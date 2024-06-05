
// 'Pipeline: Declarative' jenkins plugin required: https://plugins.jenkins.io/pipeline-model-definition/ https://www.jenkins.io/doc/pipeline/steps/pipeline-model-definition/
pipeline {
    
    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
    }

    parameters {
        string(name: 'TN_ID', defaultValue: '', description: 'Trial Network Identifier. MANDATORY')
        choice(name: 'DEPLOYMENT_SITE', choices: ['uma', 'athens', 'fokus', 'oulu'], description: 'Site where the deployment was made. Choose between uma, athens, fokus or oulu. MANDATORY')
        string(name: 'TNLCM_CALLBACK', defaultValue: 'http://tnlcm-ip:5000/tnlcm/callback/', description: 'URL of the TNLCM to notify the results. MANDATORY')
        string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library.git', description: '6G-Library repository HTTPS URL. Leave it as-is unless you want to test your own fork')
        string(name: 'LIBRARY_BRANCH', defaultValue: 'main', description: 'LIBRARY_URL branch to use. Leave it as-is unless you want to test your own branch')
        string(name: 'SITES_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Sandbox-Sites.git', description: '6G-Library-Sites repository HTTP URL. Leave it as-is unless you want to test your own fork')
        string(name: 'SITES_BRANCH', defaultValue: 'main', description: 'SITES_URL branch to use. Leave it as-is unless you want to test your own branch')
        booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG. Files will not be purged after the pipeline execution')
    }

    // Enviromental variables inherited from Jenkins Credentials
    environment {
        // Github token to clone the 6G-Sandbox-Sites repository
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
        // ONE_XMLRPC = credentials('ONE_XMLRPC')  // Try to remove
        ONE_AUTH = credentials('ONE_AUTH')
        ONE_URL = credentials('ONE_URL')  // Add to Jenkins

        // AWS Terraform Provider envirmental variables (for the MinIO S3 storage) https://registry.terraform.io/providers/hashicorp/aws/2.54.0/docs#environment-variables
        AWS_ACCESS_KEY_ID = credentials('MINIO_KEY')
        AWS_SECRET_ACCESS_KEY = credentials('MINIO_SECRET')
    }

    stages {
        stage('Stage 1: Clone 6G-Sandbox-Sites repository') {
            steps {
                echo("PURGING TRIAL NETWORK: ${TN_ID}") 
                echo 'Stage 1: Clone 6G-Sandbox-Sites repository'
                script {
                    def gitUrlWithoutGitAt = "${params.SITES_URL}".replace('https://', '')
                    def gitUrlWithToken = "https://${env.GITHUB_JENKINS}@${gitUrlWithoutGitAt}"
                    sh "git clone -b ${params.SITES_BRANCH} $gitUrlWithToken"
                }
            }
        }

        stage('Stage 2: Run playbook to destroy the selected Trial Network') {
            steps {               
              // "Ansible" jenkins plugin required: https://plugins.jenkins.io/ansible/#plugin-content-declarative-1  https://www.jenkins.io/doc/pipeline/steps/ansible/#ansibleplaybook-invoke-an-ansible-playbook
              // "SSH credentials" plugin required: https://plugins.jenkins.io/ssh-credentials/
                ansiblePlaybook(
                    credentialsId: 'remote_ssh',
                    extraVars: [
                        tn_id: "${params.TN_ID}",
                        deployment_site: "${params.DEPLOYMENT_SITE}",
                        tnlcm_callback: "${params.TNLCM_CALLBACK}}",
                        debug: "${params.DEBUG}",
                        workspace: "${WORKSPACE}",
                    ],
                    playbook: "${WORKSPACE}/.global/cac/tn_destroy.yaml"
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
                if (params.DEBUG == 'false') {
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