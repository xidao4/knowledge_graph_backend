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
        stage('Image Push'){
            agent{
                label 'master'
            }
            steps{
                echo 'Image Push Stage'
                sh "docker tag backend-coin:${BUILD_ID} ykxixi/backend-coin:${BUILD_ID}"
                sh "docker login --username=ykxixi -p dockerdocker"
                sh "docker push ykxixi/backend-coin:${BUILD_ID}"
            }
        }
        stage('deploy'){
            agent{
                label 'master'
            }
            steps{
                sh 'docker pull ykxixi/backend-coin:${BUILD_ID}'
                sh "if (ps -ef| grep java|grep backend-coin) then (docker container stop backend-coin && docker container rm backend-coin) fi"
                sh "docker run -p 8001:8001 --name backend-coin -v /log:/log -d ykxixi/backend-coin:${BUILD_ID}"
            }
        }
    }
}
