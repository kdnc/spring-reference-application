# Auth Type "Basic"

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 07. Customizing Spring Security

## Overview
How to use auth type "basic" in Spring security.

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. src/main/webapp/WEB-INF/config/security-config.xml - Declaration of basic auth type

		<http auto-config="true">
			<intercept-url pattern="/**" access="ROLE_USER" />
			<http-basic />
		</http>

## Project notes

Additional project notes can be found in exercise-files/7-springsec-fundamentals-slides.pdf file in the video tutorial.

## Server deployment

### Database import

1. Open a command line and navigate to the sql folder in the project.
		
		mysql -uroot -p123

2. In the mysql console import the .sql file
		
		source fitnessTracker.sql

### Start Wildfly
1. Open a command line and navigate to the root of the Wildfly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/startup.sh
        For Windows: WILDFLY_HOME\bin\startup.bat

### Build and Deploy the application
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and deploy the archive:

        mvn wildfly:deploy  

4. This will deploy `target/auth-type-basic.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - Login to the system

1. Navigate to the following url.       
<http://localhost:8080/auth-type-basic/>
2. A login dialog box should be popped up by browser
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Clear the cookies.
6. Navigate to the following url.       
<http://localhost:8080/auth-type-basic/>
7. Login to the application with following credentials:
	* username - admin
	* password - 123
8. You should be able to successfully login and the welcome page should be displayed.