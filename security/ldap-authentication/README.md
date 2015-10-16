# LDAP authentication

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 09. Authentication using LDAP

## Overview
How to authenticate using LDAP

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. src/main/users.ldif
	- The .ldif file which contains user information
	- We are using Spring Security test ldap server

2. src/main/webapp/WEB-INF/config/security-config.xml
	- Change Authentication Provider
		- ldap-authentication-provider tag element
		- Similar to jdbc-user-service 
		- Contains attributes for most common configurations:
			- group-search-filter 
			- group-search-base 
			- user-search-base 
			- user-search-filter 
		- Can be combined with a user-details-service element
	
				<authentication-manager>
					<ldap-authentication-provider 
						group-search-filter="member={0}"
						group-search-base="ou=groups"
						user-search-base="ou=people"
						user-search-filter="uid={0}" />
				</authentication-manager>

	- Ldap Server Configuration (Test Server)
		- ldap-server tag element
		- Helper element to connect to ldap server 
		- Can be used to create a test server 
			- Loads an ldif file
			- Default port 389 can be overridden

					<ldap-server ldif="classpath:users.ldif"  />

3. pom.xml
	- Need three additional jars 
		
			<dependency>
				<groupId>org.slf4j</groupId>
				<artifactId>slf4j-simple</artifactId>
				<version>1.5.6</version>
			</dependency>
			
			<dependency>
				<groupId>org.apache.directory.server</groupId>
				<artifactId>apacheds-all</artifactId>
				<version>1.5.5</version>
			</dependency>
			
			<dependency>
				<groupId>org.springframework.security</groupId>
				<artifactId>spring-security-ldap</artifactId>
				<version>3.2.0.RELEASE</version>
			</dependency>


	
## Project notes

Additional project notes can be found in exercise-files/9-springsec-fundamentals-slides.pdf file in the video tutorial.

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

4. This will deploy `target/ldap-authentication.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - "scott" user should be able to login

1. Navigate to the following url.       
<http://localhost:8080/ldap-authentication/>
2. You should be redirected to the <http://localhost:8080/ldap-authentication/login.html> page.
3. Login to the application with following credentials:
	* username - scott
	* password - wombat
4. You should be able to successfully login and the welcome page should be displayed.
