pipeline {

	agent any
	
	environment{
		PROJECT_ID = 'pure-tracer-360211'
		CLUSTER_NAME = 'kubernetes-cluster-lahiruwijesekara'
		LOCATION = 'us-central1'
		CREDENTIALS_ID = 'kubernetes'
	
	}

   stages {
        stage('Build') {
            steps {
                bat returnStatus: true, script: '''cd env.WORKSPACE
				mvn clean install -DskipTests'''
            }
        }
        stage('Test') {
            when {
                branch 'main'  
            }
            steps {
                bat returnStatus: true, script: 'mvn test'
            }
        }
        stage('Sonar Analysis') {
            when {
                branch 'develop'  
            }
            steps {
                bat returnStatus: true, script: '''mvn clean verify sonar:sonar \\
				-Dsonar.projectKey=sonar-lahiruwijesekara \\
				-Dsonar.host.url=http://localhost:9000 \\
				-Dsonar.login=admin \\
				-Dsonar.password=17057@Nagarro'''
            }
        }
		stage('Docker build') {
            steps {
                withEnv(['IMAGE_NAME=\'lahirume/i-lahiruwijesekara-\'+env:BRANCH_NAME+\':latest\'']) {
					bat returnStatus: true, script: 'docker build -t env:IMAGE_NAME .'
				}
            }
        }
        stage('Push docker image'){
        	steps{
        			script{
        				echo "Push Docker Image"
        				withCredentials([string(credentialsId:'dockerhub',variable:'dockerhub')]){
        				bat "docker login -u lahirume -p ${dockerhub}"
        				bat "docker push ${env:IMAGE_NAME}"
        				}
        			}
        	}
        }
        stage('Deploy to Dev K8s') {
        when {
                branch 'develop'
            }
		    steps{
			    withCredentials([file(credentialsId: 'kubernetes', variable: 'GC_KEY')]) {
			    bat("gcloud auth activate-service-account --key-file=${GC_KEY}")
			    bat("gcloud container clusters get-credentials kubernetes-cluster-lahiruwijesekara --region us-central1 --project pure-tracer-360211")
			  	bat("kubectl config set-context --current --namespace=develop")
			  	bat("kubectl apply -f deployment.yml")
			  }
			 }
	    }
     }
  }