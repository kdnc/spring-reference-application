# Spring security conditional rendering and displaying user details with tag library

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 05. Spring Security Client Integration

## Overview
How to display user details and have conditional rendering based on authorization using spring security tag library.

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. pom.xml - Maven dependency

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>3.2.0.RELEASE</version>
		</dependency>

2. src/main/webapp/WEB-INF/index.jsp - Tag lib declaration

		<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

3. src/main/webapp/WEB-INF/index.jsp - Displaying user details

		<h1>
            Welcome to Fitness Tracker <sec:authentication property="name"/>!
      	</h1>

4. src/main/webapp/WEB-INF/index.jsp - Conditional rendering based on authorization

		<sec:authorize ifAnyGranted="ROLE_ADMIN">
	        <a class="btn btn-primary" href="editGoal.html">
	          Edit Goal
	        </a>
        </sec:authorize>

## Project notes

Additional project notes can be found in exercise-files/5-springsec-fundamentals-slides.pdf file in the video tutorial.

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

4. This will deploy `target/conditional-rendering-and-displaying-user-details-with-tag-library.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - Displaying logged in user details

1. Navigate to the following url.       
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/>
2. You should be redirected to the login page:
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/spring_security_login/>
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. In the welcome page observe the following output which prints username.

		Welcome to Fitness Tracker user!
5. Navigate to the following url.       
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/spring_security_login/>
6. Login to the application with following credentials:
	* username - admin
	* password - 123
7. In the welcome page observe the following output which prints username.

		Welcome to Fitness Tracker admin!

### Test scenario 2 - Conditional rendering based on authorization

1. Navigate to the following url.       
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/>
2. You should be redirected to the login page:
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/spring_security_login/>
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. In the welcome page observe that the page does not have a button named "Edit Goal".
5. Navigate to the following url.       
<http://localhost:8080/conditional-rendering-and-displaying-user-details-with-tag-library/spring_security_login/>
6. Login to the application with following credentials:
	* username - admin
	* password - 123
7. In the welcome page observe that the page has a button named "Edit Goal".