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
		stage('Sanity check') {
		    steps {
		        echo "-=- Sanity Check Test project -=-"
		        sh 'mvn --batch-mode checkstyle:checkstyle pmd:pmd'
		    }
		    post {
		        always {
		            recordIssues enabledForFailure: true, tools: [checkStyle()]
		            recordIssues enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml')
		        }
		    }
		}
		stage('SonarQube Report') {
		    steps {
		        echo "-=- Running analyse with sonar -=-"
                withSonarQubeEnv('MySonar') {
                    sh 'mvn sonar:sonar'
                }
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
		        sh 'docker run -d --name=simple-boot -p 8180:8081 jhr1971/simple-boot'
		     }
		}
    }
}
