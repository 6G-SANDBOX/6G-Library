pipeline {

  agent any

  options {
      timeout(time: 45, unit: 'MINUTES')
      //retry(1)
  }

  parameters {
    string(name: 'TN_ID', defaultValue: '', description: 'TRIAL NETWORK IDENTIFIER')
    string(name: 'LIBRARY_COMPONENT_NAME', defaultValue: '', description: '6G LIBRARY COMPONENT')
    string(name: 'DEPLOYMENT_SITE', defaultValue: 'uma', description: 'Site where deploy')
    booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG')
    base64File (name: 'FILE', description: 'YAML file that contains variables needed to deploy the component')
    booleanParam(name: 'DEBUG', defaultValue: false, description: 'Enable DEBUG')
    //string(name: 'TNLCM_CALLBACK', defaultValue: 'https://tnlcm.uma/TN/ID/callback', description: 'URL of the TNLCM to notify result')   
  }

  environment {
      TN_ID="${params.TN_ID}"
      LIBRARY_COMPONENT_NAME="${params.LIBRARY_COMPONENT_NAME}"
      DEPLOYMENT_SITE="${params.DEPLOYMENT_SITE}"
      FILE_PATH="${WORKSPACE}/${params.LIBRARY_COMPONENT_NAME}/private/tnlcm_vars.yaml"
      DEBUG="${params.DEBUG}"
      OPENNEBULA_API_CREDENTIALS = credentials('OPENNEBULA_API_CREDENTIALS')
      OPENNEBULA_USERNAME = credentials('OPENNEBULA_TNLCM_USERNAME')
      OPENNEBULA_PASSWORD = credentials('OPENNEBULA_TNLCM_PASSWORD')
      OPENNEBULA_ENDPOINT = credentials('OPENNEBULA_ENDPOINT')
      OPENNEBULA_FLOW_ENDPOINT = credentials('OPENNEBULA_FLOW_ENDPOINT')
      OPENNEBULA_INSECURE = credentials('OPENNEBULA_INSECURE')
      ONE_XMLRPC = credentials('ONE_XMLRPC')
      ONE_AUTH = credentials('ONE_AUTH')
      AWS_ACCESS_KEY_ID = credentials('MINIO_KEY')
      AWS_SECRET_ACCESS_KEY = credentials('MINIO_SECRET')
      //ANSIBLE
      ANSIBLE_REMOTE_USER = credentials('ANSIBLE_REMOTE_USER')
      ANSIBLE_CONNECTION_PASSWORD_FILE = credentials('ANSIBLE_CONNECTION_PASSWORD_FILE')
      //ANSIBLE_BECOME_PASSWORD_FILE = credentials('ANSIBLE_BECOME_PASSWORD_FILE')      
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

    // stage('Stage 1: Clone 6G library repository') {
    //   steps {
    //       dir ("${env.WORKSPACE}/") {
    //           sh """
    //           git clone --single-branch --branch ${LIBRARY_BRANCH} ${LIBRARY_URL} .
    //           """
    //       }
    //   }
    // }      
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
      }
  }
}
