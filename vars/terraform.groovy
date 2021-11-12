def call() {
    node {
        podTemplate(label: 'terraform',
                containers: [
                        containerTemplate(name: 'terraform', image: 'hashicorp/terraform:1.0.11', ttyEnabled: true, command: 'cat')
                ]) {
            checkout scm
            node("terraform") {
                checkout scm
                container("terraform") {
                    stage('terraform init') {
                        sh "terraform init"
                    }
                    stage('terraform plan') {
                        sh "terraform plan"
                    }
                    stage('terraform init') {
                        sh "terraform init"
                    }
                }
            }
        }
    }
}