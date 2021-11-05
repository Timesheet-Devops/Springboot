pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q Springboot"
                bat "git clone https://github.com/Timesheet-Devops/Springboot.git"
                bat "cd Springboot"
                bat "git checkout 'log4j&testEntreprise'"
                bat "mvn clean"
            }
        }
        stage('install') {
            steps {
                bat "mvn install"
            }
        }
        stage('test') {
            steps {
                bat "mvn test"
            }
        }
        stage('package') {
            steps {
                bat "mvn package"
            }
           
     	}
     	stage ("Analyse avec Sonar"){
			steps{
				bat """mvn sonar:sonar"""
			}
		}
        
    }
}