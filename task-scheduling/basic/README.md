## Table of Contents
**[Resource location](#resource-location)**
**[Overview](#overview)**    
**[System requirements](#system-requirements)**  
**[Start Wildfly](#start-wildfly)**  
**[Build and Deploy the application](#build-and-deploy-the-application)**  
**[Access the application](#access-the-application)**  
**[Undeploy the Archive](#undeploy-the-archive)**  

## Resource location
* Web article - <https://spring.io/guides/gs/scheduling-tasks/>
* Git repository - <https://github.com/spring-guides/gs-scheduling-tasks>

## Overview
You’ll build an application that prints out the current time every five seconds using Spring’s `@Scheduled` annotation.

## System requirements
All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.1 or better.

The application this project produces is designed to be run on Wildfly.

## Start Wildfly
1. Open a command line and navigate to the root of the Wildfly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/startup.sh
        For Windows: WILDFLY_HOME\bin\startup.bat

## Build and Deploy the application
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and deploy the archive:

        mvn wildfly:deploy  

4. This will deploy `target/gs-scheduling-tasks.war` to the running instance of the server.

## Access the application
Check the server log file to see that current time being printed every 5 seconds with the following message.

	[...]
	The time is now 13:10:00
	The time is now 13:10:05
	The time is now 13:10:10
	The time is now 13:10:15

## Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy