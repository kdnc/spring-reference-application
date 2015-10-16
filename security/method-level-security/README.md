# Method level security

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
How to enable method level security

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. src/main/java/com/pluralsight/controller/GoalController.java
	- @PreAuthorize - most common 
	- @PostAuthorize - ran after method executes 

			@PreAuthorize("hasRole('ROLE_ADMIN')")
			public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) { }

2. src/main/webapp/WEB-INF/config/servlet-config.xml
	- <global-method-security /> 
		- pre-post-annotations 
		- secured-annotations 
		- jsr250-annotations 
	- Spring Context matters
		- We have two contexts in our app 
			- mvc context 
				- loaded by servlet
			- security (and everything else) context
				- loaded by loader listener
		- Since we have to secure the method in Controller class we put the tag element to sevlet-config.xml file.


				<security:global-method-security pre-post-annotations="enabled" />
	
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

4. This will deploy `target/method-level-security.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - ROLE_USER should not be able to add a goal

1. Navigate to the following url.       
<http://localhost:8080/method-level-security/>
2. You should be redirected to the <http://localhost:8080/method-level-security/login.html> page.
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Click on the "Add Goal" button.
6. You should see <http://localhost:8080/method-level-security/addGoal.html> page.
7. Click on "Enter Goal Minutes" button.
8. Error should be displayed. Observe the following error.
	
		Request method 'POST' not supported



5. Clear the cookies.
6. Navigate to the following url.       
<http://localhost:8080/enabling-security-with-expressions/>
7. You should be redirected to the <http://localhost:8080/enabling-security-with-expressions/login.html> page.
8. Login to the application with following credentials:
	* username - admin
	* password - 123
9. You should be able to successfully login and the welcome page should be displayed.

### Test scenario 2 - ROLE_ADMIN should be able to add a goal


1. Navigate to the following url.       
<http://localhost:8080/method-level-security/>
2. You should be redirected to the <http://localhost:8080/method-level-security/login.html> page.
3. Login to the application with following credentials:
	* username - admin
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Click on the "Add Goal" button.
6. You should see <http://localhost:8080/method-level-security/addGoal.html> page.
7. Click on "Enter Goal Minutes" button.
8. You should be redirected to the welcome page <http://localhost:8080/method-level-security/>.
9. Check the server log and observe the following log.

		10:34:06,880 INFO  [stdout] (default task-28) result has errors: false
		10:34:06,881 INFO  [stdout] (default task-28) Goal set: 10