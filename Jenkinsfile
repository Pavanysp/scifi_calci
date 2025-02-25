pipeline {
    agent any

    environment {
        MAVEN_HOME = "/usr/share/maven"
        PATH = "$PATH:$MAVEN_HOME/bin"
    }

    stages {
        stage('Setup Permissions') {
            steps {
                script {
                    sh '''
                    echo "Granting permissions to Jenkins user..."
                    sudo usermod -aG sudo jenkins
                    sudo usermod -aG docker jenkins  # If using Docker later
                    sudo chown -R jenkins:jenkins /var/lib/jenkins/workspace/
                    '''
                }
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/ScientificCalculator.git'
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
    }

    post {
        success {
            echo "Build and tests were successful!"
        }
        failure {
            echo "Build failed! Check the logs."
        }
    }
}
