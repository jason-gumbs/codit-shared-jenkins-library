def call() {
    node {
        podTemplate(label: 'terraform',
                containers: [
                        containerTemplate(name: 'terraform', image: 'hashicorp/terraform:1.0.11', ttyEnabled: true, command: 'cat')
                ],
                serviceAccount: 'cd-jenkins')
        {
            publishChecks detailsURL: 'http://35.239.53.128:8080/', name: 'Monster CI/CD', summary: 'Check code through pipeline', text: 'you can publish checks in pipeline script', title: 'Code Checker'
            checkout scm
            node("terraform") {
                ansiColor('xterm') {
                    dir("dev") {
                        container("terraform") {
                            stage('terraform init') {
                                sh "terraform init"
                            }
                            stage('terraform plan') {
                                sh "terraform plan"
                            }
//                        stage('terraform init') {
//                            sh "terraform init"
//                        }
                        }
                    }
                }
            }
        }
    }
}