# Spring MVC and Logback SLF4j example

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - http://www.mkyong.com/spring-mvc/spring-mvc-logback-slf4j-example/

## Overview
- How to setup slf4j and logback in a Spring MVC web application
- By default, Spring is using the Jakarta Commons Logging API (JCL).

## Important code blocks

Exclude commons-logging from spring-core

    <dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-core</artifactId>
		<version>${spring.version}</version>
		<exclusions>
		  <exclusion>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
		  </exclusion>
		</exclusions>
	</dependency>

Bridge the Spring’s logging from JCL to SLF4j, via jcl-over-slf4j

    <dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>jcl-over-slf4j</artifactId>
		<version>${jcl.slf4j.version}</version>
	</dependency>

Include logback as dependency

	<dependency>
		<groupId>ch.qos.logback</groupId>
		<artifactId>logback-classic</artifactId>
		<version>${logback.version}</version>
	</dependency>

Create a `logback.xml` in the `src/main/resources` folder

	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
	    <layout class="ch.qos.logback.classic.PatternLayout">
		<Pattern>
			%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
		</Pattern>
	    </layout>
	</appender>

	<logger name="com.mkyong.helloworld" level="debug" additivity="false">
		<appender-ref ref="STDOUT" />
	</logger>

Using in the app

	logger.debug("welcome() is executed, value {}", "mkyong");
	logger.error("This is Error message", new Exception("Testing"));


## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 4.1.6.RELEASE
- Logback 1.1.3
