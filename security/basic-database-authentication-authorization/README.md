# Spring Security Basic Database Authentication and Authorization

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Access the application](#access-the-application)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - Chapter 04. User Storage in a Database

## Overview
How to secure a Spring MVC application with database backed authentication and authorization

## System requirements
All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.1 or better.

The application this project produces is designed to be run on Wildfly.

## Project notes

Additional project notes can be found in exercise-files/4-springsec-fundamentals-slides.pdf file in the video tutorial.

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

4. This will deploy `target/basic-database-authentication-authorization.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Access the application
The application can be tested with following URLs:       
* <http://localhost:8080/basic-database-authentication-authorization/>

You should be redirected to the login page:
* <http://localhost:8080/basic-database-authentication-authorization/spring_security_login/>

Login to the application with following credentials:
* username - user
* password - 123
   
After entering to the application, you might need to clear the cookies to logout from the application.