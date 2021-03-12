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
                sh 'mvn -DskipTests=true package '
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
                sh "if (ps -ef| grep java|grep backend-coin) then (docker stop backend-coin && docker rm backend-coin) fi"
                sh "docker run -p 8001:8001 --name backend-coin -v /log:/log -d backend-coin:${BUILD_ID}"
            }
        }
    }
}
