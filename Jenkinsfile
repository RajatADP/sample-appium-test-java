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
        stage('Install pcloudy Jar') {
                    steps {
                        dir('src/main/java/pcoudyConnector') {
                        sh 'mvn install:install-file -Dfile=pCloudy-java-connector-11.0.7-jar-with-dependencies.jar -DgroupId=pCloudy-java-connector -DartifactId=pCloudy-java-connector -Dversion=11.0.7 -Dpackaging=jar'
                        }
                    }
        }
        stage('Run Contract Tests') {
            steps {
                  sh 'mvn clean test'
            }
        }

    }

}