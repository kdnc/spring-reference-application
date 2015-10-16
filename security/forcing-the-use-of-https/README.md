# Forcing the use of HTTPS

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 10. Forcing the use of HTTPS

## Overview
How to use Spring to force requests over HTTPS

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Apache Tomcat 7.0.35

## Important code blocks

1. src/main/webapp/WEB-INF/config/security-config.xml
	- requires-channel="https"
		
			<intercept-url pattern="/login.html" access="permitAll" requires-channel="https"/>
			<intercept-url pattern="/loginFailed.html" access="permitAll" requires-channel="https"/>
			<intercept-url pattern="/logout.html" access="permitAll" requires-channel="https"/>
			<intercept-url pattern="/403.html" access="permitAll" requires-channel="https"/>
			<intercept-url pattern="/**" access="hasRole('ROLE_USER')" requires-channel="https"/>

	
## Project notes

- To use HTTPS it requires a certificate.
- For development a self signed cert is fine, but not for production.

Additional project notes can be found in exercise-files/10-springsec-fundamentals-slides.pdf file in the video tutorial.

## Server deployment

### Generating certificate

1. Open command prompt.
2. Navigate to the root of the Tomcat directory

		cd D:\ProgramFiles\apache-tomcat-7.0.35\bin\

3. Enter below command to generate a certificate named "tomcat" in the "C:\nuwan\dev\certificates\tomcat" folder.

		keytool -genkey -alias tomcat -keyalg RSA -keystore C:\nuwan\dev\certificates\tomcat

4. Enter details as below when above command is executed.

		
		$> keytool -genkey -alias tomcat -keyalg RSA -keystore C:\nuwan\dev\certificates\tomcat

		SA -keystore C:\nuwan\dev\certificates\tomcat
		Enter keystore password: changeit
		Re-enter new password: changeit
		What is your first and last name?
		  [Unknown]:  Nuwan Chamara
		What is the name of your organizational unit?
		  [Unknown]:  test
		What is the name of your organization?
		  [Unknown]:  test
		What is the name of your City or Locality?
		  [Unknown]:  test
		What is the name of your State or Province?
		  [Unknown]:  test
		What is the two-letter country code for this unit?
		  [Unknown]:  US
		Is CN=Nuwan Chamara, OU=test, O=test, L=test, ST=test, C=US correct?
		  [no]:  y		
		Enter key password for <tomcat>
		        (RETURN if same as keystore password):

### Server configuration

1. Navigate to the "conf" folder inside Tomcat root directory and open "server.xml" file.

		D:\ProgramFiles\apache-tomcat-7.0.35\conf\server.xml

2. Uncomment port 8443 connector and edit it as follows.

		<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
           maxThreads="150" scheme="https" secure="true"
           clientAuth="false" sslProtocol="TLS" 
		   keystoreFile="C:\nuwan\dev\certificates\tomcat"
		   keystorePassword="changeit" />

### Database import

1. Open a command line and navigate to the sql folder in the project.
		
		mysql -uroot -p123

2. In the mysql console import the .sql file
		
		source fitnessTracker.sql

### Start Tomcat
1. Open a command line and navigate to the root of the Tomcat directory.
2. The following shows the command line to start the server:

        For Linux:   TOMCAT_HOME/bin/startup.sh
        For Windows: TOMCAT_HOME\bin\startup.bat

### Build and Deploy the application
1. Make sure you have started the Tomcat Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and deploy the archive:

        mvn tomcat7:deploy  

4. This will deploy `target/forcing-the-use-of-https.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Tomcat Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn tomcat7:undeploy

## Tests

### Test scenario 1 - When application is requested in HTTP port the request should force to use HTTPS

1. Navigate to the following url.       
<http://localhost:8080/forcing-the-use-of-https/>
2. The url should be changed to <https://localhost:8443/forcing-the-use-of-https/> page.
3. Browser should display a warning that "The site's security certificate is not trusted"
4. Ignore the warning and proceed to display.
5. You should be redirected to <https://localhost:8443/forcing-the-use-of-https/login.html> page.
3. Login to the application with following credentials:
	* username - scott
	* password - wombat
4. You should be able to successfully login and the welcome page should be displayed.
