# Enabling security with expressions

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 08. Enabling Security with Expressions

## Overview
How to enable security with "expressions"

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. src/main/webapp/WEB-INF/config/security-config.xml
	- "use-expressions" Attribute of http element 
	- Simplify boolean logic into an expression 
		Built-in expressions: 
			hasRole 
			hasAnyRole 
			permitAll 
			hasPermission

		<http auto-config="true" use-expressions="true" >
			<intercept-url pattern="/login.html" access="permitAll"/>
			<intercept-url pattern="/loginFailed.html" access="permitAll"/>
			<intercept-url pattern="/logout.html" access="permitAll"/>
			<intercept-url pattern="/403.html" access="permitAll"/>
			<intercept-url pattern="/**" access="hasRole('ROLE_USER')" />
			<form-login login-page="/login.html" authentication-failure-url="/loginFailed.html"/>
			<logout logout-success-url="/logout.html"/>
			<access-denied-handler error-page="/403.html"/>
		</http>

## Project notes

Additional project notes can be found in exercise-files/8-springsec-fundamentals-slides.pdf file in the video tutorial.

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

4. This will deploy `target/enabling-security-with-expressions.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - Login to the system

1. Navigate to the following url.       
<http://localhost:8080/enabling-security-with-expressions/>
2. You should be redirected to the <http://localhost:8080/enabling-security-with-expressions/login.html> page.
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Clear the cookies.
6. Navigate to the following url.       
<http://localhost:8080/enabling-security-with-expressions/>
7. You should be redirected to the <http://localhost:8080/enabling-security-with-expressions/login.html> page.
8. Login to the application with following credentials:
	* username - admin
	* password - 123
9. You should be able to successfully login and the welcome page should be displayed.
