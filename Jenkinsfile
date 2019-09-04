pipeline {
	agent {
		label 'maven'
	}

	post {
		always {
			sh '''
				echo "post build step"
			'''
		}

		failure {
			sh '''
				echo "failure"
			'''
		}

		success {
			sh '''
				echo "success"
			'''
		}
	}

	environment {
		MY_BUILD_NAME = "springboot-demo"
		MY_BUILD_NAME_BINARY = "${MY_BUILD_NAME}-binary"
	}

    options {
		buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {

        stage('Env') {
            steps {
                sh '''
				env | sort
				env | sort | grep berlin
				'''
            }
        }

        stage('Checkout') {
            steps {
				checkout scm
            }
        }

        stage('Build') {
            steps {
				sh '''
				mvn -Dmaven.test.failure.ignore=false \
					clean package
				'''
            }
        }

        stage('Prepare Buildconfig and Imagestream') {
            steps {
				sh '''
				oc new-build --binary=true --name="$MY_BUILD_NAME_BINARY" --image-stream=redhat-openjdk18-openshift --to="$MY_BUILD_NAME" || true
				oc create dc ${MY_BUILD_NAME} --image="docker-registry.default.svc:5000/wilm028-berlin/${MY_BUILD_NAME}:latest" && oc set triggers dc/$MY_BUILD_NAME --from-image=${MY_BUILD_NAME}:latest -c default-container || true
				'''
            }
        }

        stage('Build Image') {
            steps {
				sh '''
				rm -rf oc-build && mkdir -p oc-build/deployments
				cp target/springboot-demo*.jar oc-build/deployments/
				oc start-build "$MY_BUILD_NAME_BINARY" --from-dir=oc-build --wait=true
				'''
            }
        }
	}

}
