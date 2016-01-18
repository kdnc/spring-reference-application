# SmartLifecycle

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 02 The Life of a Bean - 07 Use bean lifecycle callbacks

## Overview
- smartlifecyle based lifecycle callbacks used to implement bean's setup and tear down logic

## Important code blocks

implements SmartLifecycle

    @Component
    public class Astronaut implements SmartLifecycle

override start method

	@Override
    public void start()

override stop method

	@Override
    public void stop(Runnable callback)

override isAutoStartup method

	@Override
    public void isAutoStartup()

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE
