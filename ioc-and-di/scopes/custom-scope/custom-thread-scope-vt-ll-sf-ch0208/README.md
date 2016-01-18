# Custom scope

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 02 The Life of a Bean - 08 Use bean scopes

## Overview
- Custom Scope
- Bean is scoped per thread, so successive accesses of this bean in a single thread will always print out the same 'name' but in different threads the access will yeild new `ThreadAnnouncer` with unique names.

## Important code blocks
CustomScopeConfigurer

    CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
    
    Map<String, Object> scopeMap = new HashMap<String, Object>();
    scopeMap.put("thread", scope());
    
    scopeConfigurer.setScopes(scopeMap);

Scope annotation

    @org.springframework.context.annotation.Scope("thread")

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE
