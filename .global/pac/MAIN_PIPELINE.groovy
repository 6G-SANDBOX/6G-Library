
// 'Pipeline: Declarative' jenkins plugin required: https://plugins.jenkins.io/pipeline-model-definition/ https://www.jenkins.io/doc/pipeline/steps/pipeline-model-definition/
pipeline {

    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
    }

    parameters {
        string(name: 'TN_ID', defaultValue: '', description: 'Trial Network Identifier. MANDATORY')
        string(name: 'COMPONENT_TYPE', defaultValue: '', description: '6G Library Component type. MANDATORY')
        string(name: 'CUSTOM_NAME', defaultValue: '', description: 'Custom name for the component inside the Trian Network. MANDATORY except for tn_vxlan and tn_bastion')
        choice(name: 'DEPLOYMENT_SITE', choices: ['uma', 'athens', 'fokus'], description: 'Site where the deployment is being made. Choose between uma, athens or fokus. MANDATORY')
        string(name: 'TNLCM_CALLBACK', defaultValue: 'http://tnlcm-ip:5000/tnlcm/callback/', description: 'URL of the TNLCM to notify the results. MANDATORY')
        string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library.git', description: '6G-Library repository HTTPS URL. Leave it as-is unless you want to test your own fork')
        string(name: 'LIBRARY_BRANCH', defaultValue: 'main', description: 'LIBRARY_URL branch to use. Leave it as-is unless you want to test your own branch')
        string(name: 'SITES_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Sandbox-Sites.git', description: '6G-Library-Sites repository HTTP URL. Leave it as-is unless you want to test your own fork')
        string(name: 'SITES_BRANCH', defaultValue: 'main', description: 'SITES_URL branch to use. Leave it as-is unless you want to test your own branch')
        booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG. Files will not be purged after the pipeline execution')
        // 'File Parameter' jenkins plugin required: https://plugins.jenkins.io/file-parameters/
        base64File (name: 'FILE', description: 'YAML file that contains the public variables needed to deploy the component')
    }

    // Enviromental variables inherited from Jenkins Credentials
    environment {
        // URL, branch and github token to clone the 6G-Sandbox-Sites repository
        SITES_URL="${params.SITES_URL}"
        SITES_BRANCH="${params.SITES_BRANCH}"
        GITHUB_JENKINS = credentials('GITHUB_JENKINS')

        // Opennebula Terraform Provider envorimental variables https://registry.terraform.io/providers/OpenNebula/opennebula/latest/docs#environment-variables
        // OPENNEBULA_API_CREDENTIALS = credentials('OPENNEBULA_API_CREDENTIALS')
        OPENNEBULA_USERNAME = credentials('OPENNEBULA_TNLCM_USERNAME')
        OPENNEBULA_PASSWORD = credentials('OPENNEBULA_TNLCM_PASSWORD')
        OPENNEBULA_ENDPOINT = credentials('OPENNEBULA_ENDPOINT')
        OPENNEBULA_FLOW_ENDPOINT = credentials('OPENNEBULA_FLOW_ENDPOINT')
        OPENNEBULA_INSECURE = credentials('OPENNEBULA_INSECURE')

        // Values used by OpenNebula CLI commands https://docs.opennebula.io/6.8/management_and_operations/references/cli.html#shell-environment
        // And the ansible module https://docs.ansible.com/ansible/latest/collections/community/general/one_vm_module.html
        ONE_XMLRPC = credentials('ONE_XMLRPC')  // Try to remove
        ONE_AUTH = credentials('ONE_AUTH')
        ONE_URL = credentials('ONE_URL')  // Add to Jenkins

        // AWS Terraform Provider envirmental variables (for the MinIO S3 storage) https://registry.terraform.io/providers/hashicorp/aws/2.54.0/docs#environment-variables
        AWS_ACCESS_KEY_ID = credentials('MINIO_KEY')
        AWS_SECRET_ACCESS_KEY = credentials('MINIO_SECRET')
    }

    stages {
        stage('Stage 1: Import input file into the workspace') {
            steps {
                script {
                    echo("DEPLOYING DEPLOYMENT: ${TN_ID}-${COMPONENT_TYPE}-${CUSTOM_NAME}")
                    echo "Stage 1: Import input file into the workspace"
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
                echo 'Stage 2: Load Jenkins parameters into the workspace'
                script{
                    def entityName = "${params.COMPONENT_TYPE}"
                    if (!(params.COMPONENT_TYPE == "tn_bastion" || params.COMPONENT_TYPE == "tn_vxlan")) {
                        entityName += "-${params.CUSTOM_NAME}"
                    }
                    def paramsFile = "${WORKSPACE}/${params.COMPONENT_TYPE}/variables/pipeline_parameters.yaml"
                    def paramsContent = "tn_id: ${params.TN_ID}\n"
                    paramsContent += "component_type: ${params.COMPONENT_TYPE}\n"
                    paramsContent += "custom_name: ${params.CUSTOM_NAME}\n"
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
                script {
                    def gitUrlWithoutGitAt = SITES_URL.replace('https://', '')
                    def gitUrlWithToken = "https://${GITHUB_JENKINS}@${gitUrlWithoutGitAt}"
                    sh "git clone -b $SITES_BRANCH $gitUrlWithToken"
                }
            }
        }

        stage('Stage 4: Deploy the selected component') {
            steps {
                script {
                    if (env.CUSTOM_NAME) {
                        echo "Stage 4: Run ansible playbook to deploy ${TN_ID}-${COMPONENT_TYPE}-${CUSTOM_NAME} in the ${DEPLOYMENT_SITE} site"
                    } else {
                        echo "Stage 4: Run ansible playbook to deploy ${TN_ID}-${COMPONENT_TYPE} in the ${DEPLOYMENT_SITE} site"
                    }
                } 
                
              // "Ansible" jenkins plugin required: https://plugins.jenkins.io/ansible/#plugin-content-declarative-1  https://www.jenkins.io/doc/pipeline/steps/ansible/#ansibleplaybook-invoke-an-ansible-playbook
              // "SSH credentials" plugin required: https://plugins.jenkins.io/ssh-credentials/
                ansiblePlaybook(
                    credentialsId: 'remote_ssh',
                    extraVars: [
                        workspace: "${WORKSPACE}",
                        component_type: "${params.COMPONENT_TYPE}",
                        deployment_site: "${params.DEPLOYMENT_SITE}"
                    ],
                    playbook: "${WORKSPACE}/${params.COMPONENT_TYPE}/component_playbook.yaml"
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