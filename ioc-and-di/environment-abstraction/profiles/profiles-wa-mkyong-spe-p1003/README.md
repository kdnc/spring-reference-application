# Profiles

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Project notes](#project-notes)** 
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 
**[Deployment and Tests](#deployment-and-tests)**

## Resource location
- Resource - http://www.mkyong.com/spring/spring-profiles-example/

## Overview
In this tutorial, we will show you a Spring `@Profile` application, which does the following stuff :

1. Create two profiles – `dev` and `live`
2. If profile “dev” is enabled, return a simple cache manager – `ConcurrentMapCacheManager`
3. If profile “live” is enabled, return an advanced cache manager – `EhCacheCacheManager`

## Project notes

- Spring @Profile allow developers to register beans by condition. For example, register beans based on what operating system (Windows, *nix) your application is running, or load a database properties file based on the application running in development, test, staging or production environment.
- @Profile is inside spring-context.jar

## Important code blocks

"dev" profile

	@Configuration
	@Profile("dev")
	public class CacheConfigDev {	
		@Bean
	        public CacheManager concurrentMapCacheManager() {
		    	return new ConcurrentMapCacheManager("movieFindCache");
	        }
		
	}

"live" profile
	
	@Configuration
	@Profile("live")
	public class CacheConfigLive {		
		@Bean
		public CacheManager cacheManager() {
			return new EhCacheCacheManager(ehCacheCacheManager().getObject());
		}		
	}

Enable @Profile

1. For non-web application, you can enable a profile via the Spring context environment.

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//Enable a "live" profile
		context.getEnvironment().setActiveProfiles("live");
		context.register(AppConfig.class);
		context.refresh();

2. For non-web application, you can enable a profile via the system property also.

		//Enable a "dev" profile
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea 15.0 Ultimate
- JDK 1.7.0_45
- Gradle 2.1
- Spring 4.1.4.RELEASE
- Ehcache 2.9.0

## Deployment and Tests

### Deployment and test scenario demo video

[Watch video - server deployment and test scenario demo video](https://youtu.be/ohRUTexkTDU)