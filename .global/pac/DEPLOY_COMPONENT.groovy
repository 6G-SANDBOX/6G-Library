pipeline {

  agent any

  options {
      timeout(time: 45, unit: 'MINUTES')
      //retry(1)
  }

  parameters {
    string(name: 'TN_ID', defaultValue: '', description: 'TRIAL NETWORK IDENTIFIER')
    string(name: 'LIBRARY_URL', defaultValue: 'https://github.com/6G-SANDBOX/6G-Library', description: '6G LIBRARY REPOSITORY')
    string(name: 'LIBRARY_BRANCH', defaultValue: 'main', description: '6G LIBRARY BRANCH')
    string(name: 'LIBRARY_COMPONENT_NAME', defaultValue: '', description: '6G LIBRARY COMPONENT')
    string(name: 'DEPLOYMENT_SITE', defaultValue: 'uma', description: 'Site where deploy')
    base64File (name: 'FILE', description: 'YAML file that contains variables needed to deploy the component')
    booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG')
    //string(name: 'TNLCM_CALLBACK', defaultValue: 'https://tnlcm.uma/TN/ID/callback', description: 'URL of the TNLCM to notify result')   
  }

  environment {
      TN_ID="${params.TN_ID}"

      LIBRARY_URL="https://github.com/6G-SANDBOX/6G-Library"
      LIBRARY_BRANCH="${params.LIBRARY_BRANCH}"
      LIBRARY_COMPONENT_NAME="${params.LIBRARY_COMPONENT_NAME}"
      DEPLOYMENT_SITE="${params.DEPLOYMENT_SITE}"
      FILE_PATH="${WORKSPACE}/${params.LIBRARY_COMPONENT_NAME}/private/tnlcm_vars.yaml"
      DEBUG="${params.DEBUG}"
      OPENNEBULA_API_CREDENTIALS = credentials('OPENNEBULA_API_CREDENTIALS')
      OPENNEBULA_USERNAME = credentials('OPENNEBULA_USERNAME')
      OPENNEBULA_PASSWORD = credentials('OPENNEBULA_PASSWORD')
      OPENNEBULA_ENDPOINT = credentials('OPENNEBULA_ENDPOINT')
      OPENNEBULA_INSECURE = credentials('OPENNEBULA_INSECURE')
      OPENNEBULA_FLOW_ENDPOINT = credentials('OPENNEBULA_FLOW_ENDPOINT')
      AWS_ACCESS_KEY_ID = credentials('MINIO_KEY')
      AWS_SECRET_ACCESS_KEY = credentials('MINIO_SECRET')
      ANSIBLE_REMOTE_USER = credentials('ANSIBLE_REMOTE_USER')
      ANSIBLE_CONNECTION_PASSWORD_FILE = credentials('ANSIBLE_CONNECTION_PASSWORD_FILE')
      GITHUB_JENKINS = credentials('GITHUB_JENKINS')  
  }

  stages {

    stage('Stage 0: Load component deployment variables into workspace') {
      steps {
        script {
            def YAML_CONTENT = sh (
                script: 'echo $FILE | base64 -d',
                returnStdout: true)
            echo "${YAML_CONTENT}"
            writeFile(file: FILE_PATH, text: YAML_CONTENT)
          }
      }
    }  

    stage('Stage 1: Clone 6G Sites Private Values repository') {
      steps {
          dir ("${env.WORKSPACE}/") {
              sh """
              git clone https://${GITHUB_JENKINS}@github.com/6G-SANDBOX/6G-Sandbox-Sites.git
              """
          }
      }
    }      
    stage('Stage 2: Run component deployment') {
      steps {
        script {
          sh """ 
          cd ${LIBRARY_COMPONENT_NAME}/private
          ansible-playbook --extra-vars \"tn_id=${TN_ID} component_name=${LIBRARY_COMPONENT_NAME} deployment_site=${DEPLOYMENT_SITE} workspace=${WORKSPACE}\" manifest.yaml
          """
          }
      }
    }    
  }

  post {
      always {
          sh '''
          echo "DONE"
          '''
      }
      cleanup{
          script {
              if (env.DEBUG == 'false') {
                  echo 'CleanUp Workspace'
          /* clean up our workspace */
          deleteDir()
          /* clean up tmp directory */
          dir("${env.workspace}@tmp") {
              deleteDir()
          }
          /* clean up script directory */
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
