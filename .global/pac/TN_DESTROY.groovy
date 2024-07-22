
// 'Pipeline: Declarative' jenkins plugin required: https://plugins.jenkins.io/pipeline-model-definition/ https://www.jenkins.io/doc/pipeline/steps/pipeline-model-definition/
pipeline {
    
    agent any

    options {
        timeout(time: 45, unit: 'MINUTES')
    }

    parameters {
        string(name: 'TN_ID', defaultValue: '', description: 'Trial Network Identifier. MANDATORY')
        string(name: 'DEPLOYMENT_SITE', defaultValue: '', description: 'Site where the deployment is being made. E.g. uma, athens, fokus, oulu... MANDATORY')
        string(name: 'TNLCM_CALLBACK', defaultValue: 'http://tnlcm-ip:5000/tnlcm/callback/', description: 'URL of the TNLCM to notify the results. MANDATORY')
        string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library.git', description: '6G-Library repository HTTPS URL. Leave it as-is unless you want to test your own fork')
        string(name: 'LIBRARY_BRANCH', defaultValue: 'v0.2.0', description: 'LIBRARY_URL checkout to use. Valid inputs can be refs/heads/<branchName>, refs/tags/<tagName> or <commitId>. Default value can purge TNs from previous 6G-Library version.')
        string(name: 'SITES_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Sandbox-Sites.git', description: '6G-Library-Sites repository HTTP URL. Leave it as-is unless you want to test your own fork')
        string(name: 'SITES_BRANCH', defaultValue: 'refs/heads/main', description: 'SITES_URL checkout to use. Valid inputs can be refs/heads/<branchName>, refs/tags/<tagName> or <commitId>. Default value can purge TNs from previous 6G-Library version.')
        booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG. Files will not be purged after the pipeline execution')
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
        stage('Stage 1: Clone 6G-Sandbox-Sites repository') {
            steps {
                echo("PURGING TRIAL NETWORK: ${TN_ID}") 
                echo 'Stage 1: Clone 6G-Sandbox-Sites repository'
                checkout([$class: 'GitSCM',
                          branches: [[name: params.SITES_BRANCH]],
                          extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: '6G-Sandbox-Sites']],
                          userRemoteConfigs: [[url: params.SITES_URL]]
                ])
            }
        }

        stage('Stage 2: Run playbook to destroy the selected Trial Network') {
            steps {               
                ansiblePlaybook(
                    credentialsId: 'SSH_PRIVATE_KEY',
                    vaultCredentialsId: 'ANSIBLE_VAULT_PASSWORD',
                    inventory: 'localhost,',
                    extraVars: [
                        workspace: "${WORKSPACE}",
                        deployment_site: "${params.DEPLOYMENT_SITE}",
                        tn_id: "${params.TN_ID}",
                        tnlcm_callback: "${params.TNLCM_CALLBACK}}",
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