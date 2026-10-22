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


#///////////////////

pipeline {
    agent none
    environment {
        DOCKER_IMAGE = 'task-administration'
        CREDENTIALS_ID = 'docker-credential'
    }
    stages {
        stage('Build') {
            agent {
                docker { image 'maven:3.9.9-eclipse-temurin-21' }
            }
            steps {
                git branch: "docker-stage", url: 'https://github.com/Hung1607/TaskApplication.git'
                sh 'mvn clean package -DskipTests'

                // Save the built jar and Dockerfile so the next container can use them
                stash name: 'app-artifacts', includes: 'target/*.jar, Dockerfile'
            }
        }
        stage('Docker Build & Push') {
            agent {
                docker { image 'docker:latest'
                args '-v /var/run/docker.sock:/var/run/docker.sock -e HOME=/tmp --group-add 0' }
            }
            steps {
                unstash 'app-artifacts'

                sh 'docker build -t task-admin:latest .'

                withCredentials([usernamePassword(
                    credentialsId: 'docker-credential',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker tag task-admin:latest $DOCKER_USER/$DOCKER_IMAGE:latest
                        docker push $DOCKER_USER/$DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }
}
