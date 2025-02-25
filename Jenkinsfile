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
    }

    post {
        success {
            echo "Pipeline executed successfully!"
        }
        failure {
            echo "Pipeline failed! Check the logs."
        }
    }
}
