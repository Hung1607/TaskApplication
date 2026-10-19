pipeline {
    agent any

    tools {
      maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "docker-stage", url: 'https://github.com/Hung1607/TaskApplication.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Compose Build') {
                    steps {
                        sh 'docker compose -f docker-compose.yml up -d --build'
                    }
                }
        stage('Push to Docker Hub') {
                    steps {
                        withCredentials([
                            string(credentialsId: 'docker-hub-username', variable: 'DOCKER_HUB_USERNAME'),
                            string(credentialsId: 'docker-hub-token', variable: 'DOCKER_HUB_TOKEN')
                        ]) {
                            sh 'echo "$DOCKER_HUB_TOKEN" | docker login -u "$DOCKER_HUB_USERNAME" --password-stdin'
                            sh 'docker push ${DOCKER_HUB_USERNAME}/task-administration:latest'
                        }
                    }
        }

        stage('Docker Compose') {
            steps {
                sh 'docker compose -f docker-compose.yml up -d --build'
            }
        }
    }
}