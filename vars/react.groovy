def call(Map config = [:]){
        def imageName=config.imageName
        def stageName=config.stageName
        def appVersion=config.appVersion ?: $env.BUILD_NUMBER

        if (stageName == "Build"){
                sh "docker build -t ${imageName}:${appVersion}"

        }
        else if (stageName == "Push"){
                sh "docker push ${imageName}:${appVersion}"
        }
}
