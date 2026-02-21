def call(Map config=[:]){
	def usr= config.usr
	def ip= config.ip
	def stageName= config.stageName
	
	if ( stageName == "Build" ){
		sh """mvn clean package"""
	}
	else if ( stageName == "Deploy" ){
		sh """scp -o strictHostKeyChecking=no target/*war ${usr}@${ip}:/opt/tomcat/webapps/"""
	}
}
