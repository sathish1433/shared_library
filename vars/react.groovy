def call(Map config = [:]){
        def imageName=config.imageName
        def stageName=config.stageName
        def appVersion=config.appVersion ?: $env.BUILD_NUMBER
        def sshUser=config.sshUser
        def sshIP=config.sshIP
        def buildFiles=config.buildFiles
        
        if (stageName == "Build"){
                sh """
                    ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                    "mkdir -p /home/${sshUser}/${buildFiles}"
                        
                    rsync -avz \
                    --delete \
                    --exclude='.git' \
                    --exclude='node_modules' \
                    ${buildFiles}/ \
                    ${sshUser}@${sshIP}:/home/${sshUser}/${buildFiles}/  &&
                    docker build -t ${imageName}:${appVersion} .
                    """

        }
        else if (stageName == "Push"){
                
                sh """ssh -o StrictHostKeyChecking=no ${sshUser}@${sshIP} \
                        "docker push ${imageName}:${appVersion}"
                """
        }
}
