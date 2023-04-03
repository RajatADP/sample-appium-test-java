pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
      BRANCH_NAME=env.GIT_BRANCH.replace("origin/", "")
    }

    stages {
        stage('checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/'+ BRANCH_NAME]],
                    userRemoteConfigs: [[url: 'https://github.com/RajatADP/sample-appium-test-java.git']])
            }
        }
        stage('Run Contract Tests') {
                    steps {
                            sh 'mvn clean test'
                    }
        }

    }

}