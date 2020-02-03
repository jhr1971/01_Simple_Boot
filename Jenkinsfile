pipeline {
    agent any
    
    tools {
        maven 'M3'
    }
    
    stages {
        
        stage('Checkout') {
            steps {
                echo "-=- Checkout project -=-"
                git url: 'https://github.com/jhr1971/01_simple_boot.git'
            }
        }
        
        stage('Compile') {
            steps {
                echo "-=- compiling project -=-"
                sh 'mvn clean compile'
            }
            
        }
        stage('Test') {
            steps {
                echo "-=- Test project -=-"
                sh 'mvn test'
            }
            
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        
         stage('Code coverage') {
            steps {
                jacoco( 
                      execPattern: 'target/*.exec',
                      classPattern: 'target/classes',
                      sourcePattern: 'src/main/java',
                      exclusionPattern: 'src/test*'
                )
            }
        }
        
		
		
		stage('Package') {
		     steps {
		     	echo "-=- Packaging application -=-"
		        sh 'mvn package'
		     }
		}
		
		stage('Docker build') {
		     steps {
		     	echo "-=- Building Docker Image -=-"
		        sh 'docker build -t jhr1971/simple-boot .'
		     }
		}
		
		stage('Deploy the Application') {
		     steps {
		     	echo "-=- Deploying Docker Image -=-"
		     	sh 'docker stop simple-boot'
		     	sh 'docker rm simple-boot'
		        sh 'docker run -d --name=simple-boot -p 8180:8081 jhr1971/simple-boot'
		     }
		}
    }
}
