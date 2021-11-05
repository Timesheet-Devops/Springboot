pipeline{
	environment{
		/*registry = '193jmt5213/timesheet_devops'
		registryCredential= 'dockerHub'
		dockerImage = ''*/
	}
	agent any 
	stages{
		stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: '/log4j&testEntreprise',
					url : 'https://github.com/Timesheet-Devops/Springboot';
			}
		}

		stage ("Verification du  version Maven"){
			steps{
				bat """mvn -version"""
			}
		}

		/*stage ("Clean install ignore Test"){
			steps{
				bat """mvn clean install -Dmaven.test.skip=true"""
			}
		}*/

		stage ("Clean"){
			steps{
				bat """mvn clean"""
			}
		}

		stage ("Creation du livrable"){
			steps{
				bat """mvn package -Dmaven.test.skip=true"""
			}
		}

		stage ("Lancement des Tests Unitaires"){
			steps{
				bat """mvn test"""
			}
		}

		stage ("Analyse avec Sonar"){
			steps{
				bat """mvn sonar:sonar"""
			}
		}

		/*stage ("Deploiement"){
			steps{
				bat """mvn clean package deploy:deploy-file -DgroupId='tn.esprit.spring' -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=1 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl='http://localhost:8081/repository/maven-releases/' -Dfile='target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.war'"""
			}
		}

		stage('Building our image'){
			steps{ 
				script{ 
					dockerImage= docker.build registry + ":$BUILD_NUMBER" 
				}
			}
		}

		stage('Deploy our image'){
			steps{ 
				script{
					docker.withRegistry( '', registryCredential){
						dockerImage.push()
					} 
				} 
			}
		}

		stage('Cleaning up'){
			steps{
				bat "docker rmi $registry:$BUILD_NUMBER" 
			}
		}*/
}

	post{
		success{
			emailext body: 'Build success', subject: 'Jenkins', to:'sofien.triki1@esprit.tn'
		}
		failure{
			emailext body: 'Build failure', subject: 'Jenkins', to:'sofien.triki1@esprit.tn'
		}
	}
}