def call(Map config=[:]){
    if(config.stageName == 'Checkout'){
        git changelog: false, poll: false, url: 'https://github.com/sathish1433/python_app_cicd.git'
    }
    else if( config.stageName == 'pyenv'){
        sh """/usr/bin/python -m venv myenv && source myenv/bin/activate"""
    }
    else if ( config.stageName == 'package'){
        sh """myenv/bin/pip install -r requirements.txt"""
    }
    else if (config.stageName == 'Deploy'){
        sh """/usr/bin/python app.py"""
    }
}
