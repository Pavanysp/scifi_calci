pipeline {
    agent any

    environment {
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$PATH:$MAVEN_HOME/bin"
        DOCKER_IMAGE = "pavan020504/scientific-calculator"
        SERVER_IP = "127.0.0.1"
        SSH_KEY_PATH = "/var/lib/jenkins/.ssh/id_rsa"
    }

    stages {
        stage('Setup Permissions') {
            steps {
                script {
                    sh '''
                    echo "Granting permissions to the Jenkins user.."
                    sudo usermod -aG docker jenkins
                    
                    sudo chown -R jenkins:jenkins /var/lib/jenkins/.ssh
                    sudo chmod 700 /var/lib/jenkins/.ssh
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
                sh 'sudo docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Upload to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: '1cb7dcef-4311-43a3-a0c6-e1bee0229828', 
                                                  usernameVariable: 'DOCKER_USER', 
                                                  passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                    echo "Logging in to Docker Hub"
                    echo "${DOCKER_PASS}" | sudo docker login -u "${DOCKER_USER}" --password-stdin
                    sudo docker push ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Deploy via Ansible') {
            steps {
                script {
                    sh '''
                    echo "Creating Ansible hosts.ini file"
                    echo "[servers]" > hosts.ini
                    echo "${SERVER_IP} ansible_user=pavan ansible_ssh_private_key_file=${SSH_KEY_PATH}" >> hosts.ini

                    echo "Running the Ansible Playbook..."
                    ansible-playbook -i hosts.ini deploy.yml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully! The Scientific Calculator is successfully deployed....."
        }
        failure {
            echo "Pipeline failed! Check the logs for errors..."
        }
    }
}
