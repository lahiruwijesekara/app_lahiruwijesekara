pipeline {

	agent any

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
				-Dsonar.login=sqp_822163eb73b1f27f6aaf14716804c23d5eefc82c'''
            }
        }
		stage('Docker build') {
            steps {
                withEnv(['IMAGE_NAME=\'i-lahiruwijesekara-\'+env:BRANCH_NAME+\':latest\'']) {
					bat returnStatus: true, script: 'docker build -t env:IMAGE_NAME .'
				}
            }
        }
    }
}