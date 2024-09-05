
// 'Pipeline: Declarative' jenkins plugin required: https://plugins.jenkins.io/pipeline-model-definition/ https://www.jenkins.io/doc/pipeline/steps/pipeline-model-definition/
pipeline {

    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
    }

    parameters {
        string(name: 'TN_ID', defaultValue: '', description: 'Trial Network Identifier. Valid characters are A-Z, a-z, 0-9 and underscore _. MANDATORY')
        string(name: 'COMPONENT_TYPE', defaultValue: '', description: '6G Library Component type. MANDATORY')
        string(name: 'CUSTOM_NAME', defaultValue: '', description: 'Custom name for the component inside the Trial Network. Valid characters are A-Z, a-z, 0-9 and underscore _. MANDATORY except for tn_init (including tn_vxlan and tn_bastion)')
        string(name: 'DEPLOYMENT_SITE', defaultValue: '', description: 'Site where the deployment is being made. E.g. uma, athens, fokus, oulu... MANDATORY')
        string(name: 'TNLCM_CALLBACK', defaultValue: 'http://tnlcm-ip:5000/tnlcm/callback/', description: 'URL of the TNLCM to notify the results. MANDATORY')
        string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library.git', description: '6G-Library repository HTTPS URL. Leave it as-is unless you want to test your own fork')
        string(name: 'LIBRARY_BRANCH', defaultValue: 'refs/tags/v0.2.2', description: 'LIBRARY_URL checkout to use. Valid inputs can be refs/heads/<branchName>, refs/tags/<tagName> or <commitId>. Leave it as-is unless you want to test alternative releases/branches/commits.')
        string(name: 'SITES_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Sandbox-Sites.git', description: '6G-Library-Sites repository HTTP URL. Leave it as-is unless you want to test your own fork')
        string(name: 'SITES_BRANCH', defaultValue: 'refs/heads/main', description: 'SITES_URL checkout to use. Valid inputs can be refs/heads/<branchName>, refs/tags/<tagName> or <commitId>. Leave it as-is unless you want to test alternative releases/branches/commits.')
        booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG. Files will not be purged after the pipeline execution. WARNING: You have to manually delete the workspace from the Jenkins VM filesystem to be able to lauch new jobs if you choose this option.')
        // 'File Parameter' jenkins plugin required: https://plugins.jenkins.io/file-parameters/
        base64File (name: 'FILE', description: 'YAML file that contains the public variables needed to deploy the component')
    }

    // Enviromental variables inherited from Jenkins Credentials
    environment {
        // Opennebula Terraform Provider envorimental variables https://registry.terraform.io/providers/OpenNebula/opennebula/latest/docs#environment-variables
        // OPENNEBULA_API_CREDENTIALS = credentials('OPENNEBULA_API_CREDENTIALS')
        OPENNEBULA_USERNAME = credentials('OPENNEBULA_USERNAME')
        OPENNEBULA_PASSWORD = credentials('OPENNEBULA_PASSWORD')
        OPENNEBULA_ENDPOINT = credentials('OPENNEBULA_ENDPOINT')
        OPENNEBULA_FLOW_ENDPOINT = credentials('OPENNEBULA_FLOW_ENDPOINT')
        OPENNEBULA_INSECURE = credentials('OPENNEBULA_INSECURE')

        // AWS Terraform Provider envirmental variables (for the MinIO S3 storage) https://registry.terraform.io/providers/hashicorp/aws/2.54.0/docs#environment-variables
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
    }

    stages {
        stage('Stage 1: Import input file into the workspace') {
            steps {
                echo 'Stage 1: Import input file into the workspace'
                script {
                    def inputFile = "${WORKSPACE}/${params.COMPONENT_TYPE}/variables/input_file.yaml"

                    def fileContent = sh (
                        script: 'echo $FILE | base64 -d',
                        returnStdout: true
                    )
                    if (env.DEBUG == 'true') {
                        echo "${fileContent}"
                    }
                    writeFile file: inputFile, text: fileContent
                }
            }
        }

        stage('Stage 2: Import Jenkins parameters into the workspace') {
            steps {
                echo 'Stage 2: Import Jenkins parameters into the workspace'
                script{
                    def paramsFile = "${WORKSPACE}/${params.COMPONENT_TYPE}/variables/pipeline_parameters.yaml"
                    def paramsContent = "tn_id: ${params.TN_ID}\n"
                    paramsContent += "component_type: ${params.COMPONENT_TYPE}\n"
                    paramsContent += "custom_name: ${params.CUSTOM_NAME}\n"
                    def entityName = params.CUSTOM_NAME ? "${params.COMPONENT_TYPE}-${params.CUSTOM_NAME}" : "${params.COMPONENT_TYPE}"
                    paramsContent += "entity_name: ${entityName}\n"
                    paramsContent += "deployment_site: ${params.DEPLOYMENT_SITE}\n"
                    paramsContent += "tnlcm_callback: ${params.TNLCM_CALLBACK}\n"
                    paramsContent += "debug: ${params.DEBUG}\n"

                    writeFile file: paramsFile, text: paramsContent
                }
            }
        }

        stage('Stage 3: Clone 6G-Sandbox-Sites repository') {
            steps {
                echo 'Stage 3: Clone 6G-Sandbox-Sites repository'
                checkout([$class: 'GitSCM',
                          branches: [[name: params.SITES_BRANCH]],
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: '6G-Sandbox-Sites']],
                          userRemoteConfigs: [[url: params.SITES_URL]]
                ])
            }
        }

        stage('Stage 4: Deploy the selected component') {
            steps {
                script {
                    def entityName = params.CUSTOM_NAME ? "${params.COMPONENT_TYPE}-${params.CUSTOM_NAME}" : "${params.COMPONENT_TYPE}"
                    echo "Stage 4: Run ansible playbook to deploy ${TN_ID}-${entityName} in the ${DEPLOYMENT_SITE} site"
                } 
                ansiblePlaybook(
                    credentialsId: 'SSH_PRIVATE_KEY',
                    vaultCredentialsId: 'ANSIBLE_VAULT_PASSWORD',
                    inventory: 'localhost,',
                    extraVars: [
                        workspace:       "${WORKSPACE}",
                        tn_id:           "${params.TN_ID}",
                        component_type:  "${params.COMPONENT_TYPE}",
                        custom_name:     "${params.CUSTOM_NAME}",
                        entity_name:     "${entity_name}",                       
                        deployment_site: "${params.DEPLOYMENT_SITE}",
                        tnlcm_callback:  "${params.TNLCM_CALLBACK}",
                        debug:           "${params.DEBUG}",
                    ],
                    playbook: "${WORKSPACE}/${params.COMPONENT_TYPE}/code/component_playbook.yaml"
                )
            }
        }    
    }

    post {
        always {
            echo "PIPELINE FINISHED"
        }
        cleanup{
            // script step required to execute "Scripted Pipeline" syntax blocks into Declarative Pipelines
            script {
                if (env.DEBUG == 'false') {
                    echo 'Clean up Workspace'
                    deleteDir()
                
                    echo 'Clean up tmp directory'
                    dir("${env.workspace}@tmp") {
                        deleteDir()
                    }
                
                    echo 'Clean up script directory'
                    dir("${env.workspace}@script") {
                        deleteDir()
                    }
                } else {
                    echo 'Workspace is not deleted'
                }
            }
        }
    }
}
