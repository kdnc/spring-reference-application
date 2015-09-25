## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**  
**[Start Wildfly](#start-wildfly)**  
**[Build and Deploy the application](#build-and-deploy-the-application)**  
**[Access the application](#access-the-application)**  
**[Undeploy the Archive](#undeploy-the-archive)**  

## Resource location
* Web article - <https://spring.io/guides/gs/device-detection/>
* Git repository - <https://github.com/spring-guides/gs-device-detection>
* Additional resources 
 - <http://stackoverflow.com/questions/25792121/spring-boot-websockets-in-wildfly>

## Overview
You’ll create a Spring MVC application that detects the type of device that is accessing your web site and that switches views dynamically based on that device type.

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

4. This will deploy `target/gs-device-detection.war` to the running instance of the server.

## Access the application
The application can be tested with following URLs:       
* <http://localhost:8080/gs-device-detection/detect-device/>

In a normal desktop browser, you should see something like this.

![Noraml browser](https://spring.io/guides/gs/device-detection/images/normal-browser.png)

In a mobile browser (such as the iOS Simulator’s browser), you should see something like this.

![Mobile browser](https://spring.io/guides/gs/device-detection/images/mobile-browser.png)

In a normal tablet browser, you should see something like this.

![Tablet browser](https://spring.io/guides/gs/device-detection/images/tablet-browser.png)

## Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy