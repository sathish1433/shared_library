def call(Map config = [:]){
        def imageName=config.imageName
        def stageName=config.stageName
        def appVersion=config.appVersion ?: $env.BUILD_NUMBER
        def sshUser=config.aahUser
        def sshIP=config.sshIP

        if (stageName == "Build"){
                sh """ ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                        "docker build -t ${imageName}:${appVersion}"
                """

        }
        else if (stageName == "Push"){
                
                sh """ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                        "docker push ${imageName}:${appVersion}"
                """
        }
}
