pipeline {
    agent any

    environment {
        CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver" // Kiểm tra đường dẫn trên server
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/quachvietthanh/selenium-jenkins-demo.git'
            }
        }

        stage('Setup ChromeDriver') {
            steps {
                sh '''
                echo "🔍 Kiểm tra ChromeDriver..."
                if [ -f $CHROME_DRIVER_PATH ]; then
                    echo "✅ ChromeDriver đã tồn tại: $CHROME_DRIVER_PATH"
                    $CHROME_DRIVER_PATH --version
                else
                    echo "❌ ChromeDriver KHÔNG tồn tại! Kiểm tra lại cài đặt."
                    exit 1
                fi
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Post-Test Report') {
            steps {
                script {
                    def reports = findFiles(glob: 'target/surefire-reports/*.xml')
                    if (reports.length > 0) {
                        junit 'target/surefire-reports/*.xml'
                    } else {
                        echo "⚠️ Không tìm thấy JUnit report! Kiểm tra lại quá trình test."
                    }
                }
            }
        }
    }
}

