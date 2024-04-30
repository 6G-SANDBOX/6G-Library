
// 'Pipeline: Declarative' jenkins plugin required: https://plugins.jenkins.io/pipeline-model-definition/ https://www.jenkins.io/doc/pipeline/steps/pipeline-model-definition/
pipeline {

    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
    }

    parameters {
        string(name: 'TN_ID', defaultValue: '', description: 'Trial Network Identifier')
        string(name: 'LIBRARY_COMPONENT_NAME', defaultValue: '', description: '6G Library Component type')
        string(name: 'ENTITY_NAME', defaultValue: '', description: 'Custom name for the component inside the Trian Network')
        choice(name: 'DEPLOYMENT_SITE', choices: ['uma', 'athens', 'fokus'], description: 'Site where the deployment is being made')
        string(name: 'TNLCM_CALLBACK', defaultValue: 'http://tnlcm-ip:5000/tnlcm/callback/', description: 'URL of the TNLCM to notify the results')
        string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library', description: '6G-Library Repository URL. Leave it as-is unless you want to test your own fork')
        string(name: 'LIBRARY_BRANCH', defaultValue: 'main', description: 'LIBRARY_URL branch to use')
        booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG')
        // 'File Parameter' jenkins plugin required: https://plugins.jenkins.io/file-parameters/
        base64File (name: 'FILE', description: 'YAML file that contains variables needed to deploy the component')
    }

    // Enviromental variables inherited from Jenkins Credentials
    environment {
        // Github token to access the 6G-Sandbox-Sites repository
        GITHUB_JENKINS = credentials('GITHUB_JENKINS') 

        // Opennebula Terraform Provider envorimental variables https://registry.terraform.io/providers/OpenNebula/opennebula/latest/docs#environment-variables
        OPENNEBULA_API_CREDENTIALS = credentials('OPENNEBULA_API_CREDENTIALS')
        OPENNEBULA_USERNAME = credentials('OPENNEBULA_TNLCM_USERNAME')
        OPENNEBULA_PASSWORD = credentials('OPENNEBULA_TNLCM_PASSWORD')
        OPENNEBULA_ENDPOINT = credentials('OPENNEBULA_ENDPOINT')
        OPENNEBULA_FLOW_ENDPOINT = credentials('OPENNEBULA_FLOW_ENDPOINT')
        OPENNEBULA_INSECURE = credentials('OPENNEBULA_INSECURE')

        ONE_XMLRPC = credentials('ONE_XMLRPC')     // useless? check utility or remove
        ONE_AUTH = credentials('ONE_AUTH')         // useless? check utility or remove

        // AWS Terraform Provider envirmental variables (for the MinIO S3 storage) https://registry.terraform.io/providers/hashicorp/aws/2.54.0/docs#environment-variables
        AWS_ACCESS_KEY_ID = credentials('MINIO_KEY')
        AWS_SECRET_ACCESS_KEY = credentials('MINIO_SECRET')

        //Ansible enviromental variables https://docs.ansible.com/ansible/latest/reference_appendices/config.html
        // ANSIBLE_REMOTE_USER = credentials('ANSIBLE_REMOTE_USER')
        // ANSIBLE_CONNECTION_PASSWORD_FILE = credentials('ANSIBLE_CONNECTION_PASSWORD_FILE')
    }

    stages {
        stage('Stage 1: Import input file into the workspace') {
            steps {
                echo "Stage 1: Import ${TN_ID}-${LIBRARY_COMPONENT_NAME}-${ENTITY_NAME} input file into the workspace"
                script {
                    def inputFile = "${WORKSPACE}/${params.LIBRARY_COMPONENT_NAME}/variables/input_file.yaml"

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

        stage('Stage 2: Clone 6G-Sandbox-Sites repository') {
            steps {
                echo 'Stage 2: Clone 6G-Sandbox-Sites repository'
                dir ("${env.WORKSPACE}/") {
                    sh "git clone https://${GITHUB_JENKINS}@github.com/6G-SANDBOX/6G-Sandbox-Sites.git"
                }
            }
        }

        stage('Stage 3: Load Jenkins parameters into file') {
            steps {
                echo 'Stage 3: Load Jenkins parameters into the workspace'
                script{
                    def paramsFile = "${WORKSPACE}/${params.LIBRARY_COMPONENT_NAME}/variables/pipeline_parameters.yaml"
                    def paramsContent = "TN_ID=${params.TN_ID}\n"
                    paramsContent += "LIBRARY_COMPONENT_NAME=${params.LIBRARY_COMPONENT_NAME}\n"
                    paramsContent += "ENTITY_NAME=${params.ENTITY_NAME}\n"
                    paramsContent += "DEPLOYMENT_SITE=${params.DEPLOYMENT_SITE}\n"
                    paramsContent += "TNLCM_CALLBACK=${params.TNLCM_CALLBACK}\n"
                    paramsContent += "LIBRARY_URL=${params.LIBRARY_URL}\n"
                    paramsContent += "LIBRARY_BRANCH=${params.LIBRARY_BRANCH}\n"
                    paramsContent += "DEBUG=${params.DEBUG}\n"

                    writeFile file: paramsFile, text: paramsContent
                }
            }
        }
   
        stage('Stage 4: Deploy the selected component') {
            steps {
                echo "Stage 4: Deploy the $LIBRARY_COMPONENT_NAME component"
              // script {
              //   sh """ 
              //   cd ${LIBRARY_COMPONENT_NAME}/private
              //   ansible-playbook --extra-vars \"tn_id=${TN_ID} component_name=${LIBRARY_COMPONENT_NAME} deployment_site=${DEPLOYMENT_SITE} workspace=${WORKSPACE}\" manifest.yaml
              //   """
              //   }
                echo "Stage 3: Run ansible playbook to deploy ${TN_ID}-${LIBRARY_COMPONENT_NAME}-${ENTITY_NAME} in the ${DEPLOYMENT_SITE} site"

              // "Ansible" jenkins plugin required: https://plugins.jenkins.io/ansible/#plugin-content-declarative-1  https://www.jenkins.io/doc/pipeline/steps/ansible/#ansibleplaybook-invoke-an-ansible-playbook
                ansiblePlaybook(
                    extraVars: [
                        workspace: "${WORKSPACE}"
                    ],
                    playbook: "${WORKSPACE}/.global/cac/deploy_playbook.yaml"
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
