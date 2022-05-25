pipeline {

    agent any

    environment {
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }

    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('Build project') {
           steps{
                bat 'mvn clean install'
           }
        }
        stage('build image') {
            steps{
                bat 'docker build -t dmytrotrots/springboot-periodicals-image:latest .'
            }
        }
        stage('Login to docker') {
            steps {
        		bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
        	}
        }
        stage('Push to docker hub') {
            steps {
        	    bat 'docker push dmytrotrots/springboot-periodicals-image:latest'
        	}
        }
    }
}