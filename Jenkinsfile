pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               bat "rmdir  /s /q Springboot"
                bat "git clone https://github.com/Timesheet-Devops/Springboot.git"
                bat "git checkout 'log4j&testEntreprise'"
                bat "mvn clean -f Springboot"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f Springboot"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f Springboot"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f Springboot"
            }
        }
    }
}