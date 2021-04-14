pipeline {
    agent{
        label 'master'
    }

    stages {
        stage('Maven Build and Test') {
            agent{
                docker {
                    image 'maven:latest'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps{
                echo 'Maven Build Stage'
                sh "mvn clean package"

                echo 'Publish Test Coverage Report'
                step([$class: 'JacocoPublisher',
                   execPattern: 'target/jacoco.exec',
                   classPattern: 'target/classes'
               ])

               publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: 'target/site/jacoco',
                    reportFiles: 'index.html',
                    reportName: 'Jacoco HTML Report',
                    reportTitles: ''])
            }
	    }
        stage('Image Build'){
            agent{
                label 'master'
            }
            steps{
                echo 'Image Build Stage'
                sh "docker build . -t backend-coin:${BUILD_ID}"
            }
        }
        stage('deploy'){
            agent{
                label 'master'
            }
            steps{
                sh "if (docker ps -a |grep backend-coin) then (docker stop backend-coin && docker rm backend-coin) fi"
                sh "docker run -p 8001:8001 --name backend-coin -v /log:/log -v /datastore:/datastore -d backend-coin:${BUILD_ID}"
            }
        }
    }
}
