def call(Map config = [:]){
        def imageName=config.imageName
        def stageName=config.stageName
        def appVersion=config.appVersion ?: $env.BUILD_NUMBER
        def sshUser=config.sshUser
        def sshIP=config.sshIP
        def buildFiles=config.buildFiles
        
        if (stageName == "Build"){
                sh """ scp -o StrictHostKeyChecking=no -r ${buildFiles} ${sshUser}@${sshIP}:/home/${sshUser}/ &&
                        ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                        "docker build -t ${imageName}:${appVersion} ."
                """

        }
        else if (stageName == "Push"){
                
                sh """ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                        "docker push ${imageName}:${appVersion}"
                """
        }
}
