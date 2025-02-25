pipeline {
    agent any

    environment {
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$PATH:$MAVEN_HOME/bin"
        DOCKER_IMAGE = "pavan020504/scientific-calculator"
        DOCKER_USERNAME = "pavan020504"
        DOCKER_PASSWORD = "Pavan@2230"
        SERVER_IP = "192.168.199.230"
    }

    stages {
        stage('Setup Permissions') {
            steps {
                script {
                    sh '''
                    echo "Granting permissions to Jenkins user..."
                    sudo usermod -aG sudo jenkins
                    sudo chown -R jenkins:jenkins /var/lib/jenkins/workspace/
                    '''
                }
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Pavanysp/scifi_calci.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Containerize Application') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    sh '''
                    echo "Logging in to Docker Hub..."
                    echo "${DOCKER_PASSWORD}" | docker login -u "${DOCKER_USERNAME}" --password-stdin
                    docker push ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Run Ansible Deployment') {
            steps {
                script {
                    sh '''
                    echo "Creating Ansible hosts.ini file..."
                    echo "[servers]" > hosts.ini
                    echo "${SERVER_IP} ansible_user=pavan ansible_ssh_private_key_file=~/.ssh/id_rsa" >> hosts.ini
                    
                    echo "Running Ansible Playbook..."
                    ansible-playbook -i hosts.ini deploy.yml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully! The Scientific Calculator is deployed."
        }
        failure {
            echo "Pipeline failed! Check the logs for errors."
        }
    }
}
