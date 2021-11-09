pipeline {
 environment {
	registry = "aminbencheikh/timesheet001"
    registryCredential = 'dockerHub'
    } 
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q Springboot"
                bat "git clone https://github.com/Timesheet-Devops/Springboot.git"
                bat "cd Springboot"
                bat """git checkout Amin"""
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
				bat """mvn -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true sonar:sonar -Dsonar.projectKey=a39e42b079e89a36290a25597f1f0e79087934b4 -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a39e42b079e89a36290a25597f1f0e79087934b4 """
			}
		}
		stage ("Deploiement"){
			steps{
				bat """mvn clean -DskipTests package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.1 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1.war"""
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