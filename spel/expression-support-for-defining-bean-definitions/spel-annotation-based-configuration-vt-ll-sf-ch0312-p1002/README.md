# spel Annotation based configuration

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 
**[Deployment and Tests](#deployment-and-tests)**

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 03 Power Tools in Core Spring - 12 Get beans from strange places

## Overview
- How to use spel with annotation based configuration

## Important code blocks

Get access to a random value

	@Value("#{ T(Math).random()  }")
    private double aRandomValue ;

Get access to a system property

	@Value("#{systemProperties['user.home']}")
    private String userHome;

Get access to a file

	@Value("#{systemProperties['java.io.tmpdir']}")
    public void setIoTmpDir(String tmpDir) {
        this.ioTmpDir = new File(tmpDir);
    }

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE

## Deployment and Tests

### Deployment and test scenario demo video

[Watch video - server deployment and test scenario demo video](https://youtu.be/V7jheIvJFf0)