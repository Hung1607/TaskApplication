pipeline {
    agent any

    tool{
      maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Hung1607/TaskApplication.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Compose') {
            steps {
                sh 'docker compose up -d --build'
            }
        }
    }
}