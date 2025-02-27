pipeline {
    agent any

    environment {
        CHROME_DRIVER_PATH = "C:\\Program Files\\ChromeDriver\\chromedriver.exe" // Kiểm tra đường dẫn trên Windows
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/quachvietthanh/selenium-jenkins-demo.git'
            }
        }

        stage('Setup ChromeDriver') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                        echo "🔍 Kiểm tra ChromeDriver trên Linux..."
                        if [ -f $CHROME_DRIVER_PATH ]; then
                            echo "✅ ChromeDriver tồn tại: $CHROME_DRIVER_PATH"
                            $CHROME_DRIVER_PATH --version
                        else
                            echo "❌ ChromeDriver KHÔNG tồn tại! Kiểm tra lại cài đặt."
                            exit 1
                        fi
                        '''
                    } else {
                        bat '''
                        echo Kiểm tra ChromeDriver trên Windows...
                        if exist "%CHROME_DRIVER_PATH%" (
                            echo ✅ ChromeDriver tồn tại: %CHROME_DRIVER_PATH%
                            "%CHROME_DRIVER_PATH%" --version
                        ) else (
                            echo ❌ ChromeDriver KHÔNG tồn tại! Kiểm tra lại cài đặt.
                            exit /b 1
                        )
                        '''
                    }
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Post-Test Report') {
            steps {
                junit 'target/surefire-reports/*.xml'
                echo "✅ Test report đã được xử lý."
            }
        }
    }
}


