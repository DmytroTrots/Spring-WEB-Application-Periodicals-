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
        		bat 'docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
        	}
        }
        stage('Push to docker hub') {
            steps {
        	    bat 'docker push dmytrotrots/springboot-periodicals-image:latest'
        	}
        }
        stage('Run docker-compose') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }
}