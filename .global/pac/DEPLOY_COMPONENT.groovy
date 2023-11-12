pipeline {

  agent any

  options {
      timeout(time: 30, unit: 'MINUTES')
      //retry(1)
  }

  parameters {
    string(name: 'TN_ID', defaultValue: '', description: 'TRIAL NETWORK IDENTIFIER')
    string(name: 'LIBRARY_COMPONENT_NAME', defaultValue: '', description: '6G LIBRARY COMPONENT')
    string(name: 'LIBRARY_BRANCH', defaultValue: 'main', description: '6G LIBRARY BRANCH')
    base64File (name: 'FILE', description: 'YAML file that contains variables needed to deploy the component')
    //string(name: 'TN_TERRAFORM_STATE_URL', defaultValue: 'https://tnlcm.uma/TN/ID/tfstate', description: 'TN Terraform state')
    //string(name: 'TNLCM_CALLBACK', defaultValue: 'https://tnlcm.uma/TN/ID/callback', description: 'URL of the TNLCM to notify result')   
  }

  environment {
      LIBRARY_URL="https://github.com/6G-SANDBOX/6G-Library"
      TN_ID="${params.TN_ID}"
      LIBRARY_COMPONENT_NAME="${params.LIBRARY_COMPONENT_NAME}"
      LIBRARY_BRANCH="${params.LIBRARY_BRANCH}"
      FILE_PATH="/tmp/${params.TN_ID}_${params.LIBRARY_COMPONENT_NAME}_vars.yaml"
  }

  stages {

    stage('Stage 0: Load component deployment variables into workspace') {
      steps {
        script {
            def YAML_CONTENT = sh (
                script: 'echo $FILE | base64 -d',
                returnStdout: true)
            echo "${YAML_CONTENT}"
            // This file path is relevant because ansible will look for this file on stage 2
            writeFile(file: ${FILE_PATH}, text: YAML_CONTENT)
          }
      }
    }  

    stage('Stage 1: Clone 6G library repository') {
      steps {
          dir ("${env.WORKSPACE}/") {
              sh """
              git clone --single-branch --branch ${LIBRARY_BRANCH} ${LIBRARY_URL} .
              """
          }
      }
    }      
    stage('Stage 2: Run component deployment') {
      steps {
        script {
          sh """ 
          cd ${LIBRARY_COMPONENT_NAME}/private
          ansible-playbook manifest.yaml
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
  }  
}