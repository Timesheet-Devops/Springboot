pipeline {   environment {
	registry = "hmzlz/timesheetimage"
    registryCredential = 'dockerHub'
} agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q Springboot"
                bat "git clone https://github.com/Timesheet-Devops/Springboot.git"
                bat "cd Springboot"
                bat """git checkout hamza"""
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
		stage ("Deploiement"){
			steps{
				bat """mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=1 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.war"""
			}
		}
        stage('Building image') {
            steps{
             script {
          docker.build registry + ":$BUILD_NUMBER"
             }
            }
        }   
        
		}


        
    }