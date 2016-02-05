# EhCache based cache

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**  
**[Project notes](#project-notes)**   
**[Important code blocks](#important-code-blocks)**  
**[System requirements](#system-requirements)**  
**[Deployment and Tests](#deployment-and-tests)**  

## Resource location
- Resource - http://www.mkyong.com/spring/spring-caching-and-ehcache-example/

## Overview
In this tutorial, we will show you how to enable data caching in a Spring application, and integrate with the popular Ehcache framework.

## Project notes

- The Spring caching is in the `spring-context.jar`, to support Ehcache caching, you need to include the `spring-context-support.jar` as well.

## Important code blocks

Project Dependencies for Gradle projects

	dependencies {
		compile 'org.springframework:spring-context:4.1.4.RELEASE'
		compile 'org.springframework:spring-context-support:4.1.4.RELEASE'
		compile 'net.sf.ehcache:ehcache:2.9.0'
	}

`ehcache.xml` file, to tell Ehcache how and where to cache the data

	<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:noNamespaceSchemaLocation="ehcache.xsd" 
		updateCheck="true"
		monitoring="autodetect" 
		dynamicConfig="true">
	
		<diskStore path="java.io.tmpdir" />
		
		<cache name="movieFindCache" 
			maxEntriesLocalHeap="10000"
			maxEntriesLocalDisk="1000" 
			eternal="false" 
			diskSpoolBufferSizeMB="20"
			timeToIdleSeconds="300" timeToLiveSeconds="600"
			memoryStoreEvictionPolicy="LFU" 
			transactionalMode="off">
			<persistence strategy="localTempSwap" />
		</cache>
	
	</ehcache>

Add @Cacheable on the method you want to cache

	@Cacheable(value="movieFindCache", key="#name")
	public Movie findByDirector(String name) {
		slowQuery(2000L);
	}

Enable Caching with @EnableCaching and declared a EhCacheCacheManager

	@Configuration
	@EnableCaching
	@ComponentScan({ "com.mkyong.*" })
	public class AppConfig {
	
		@Bean
		public CacheManager cacheManager() {
			return new EhCacheCacheManager(ehCacheCacheManager().getObject());
		}
	
		@Bean
		public EhCacheManagerFactoryBean ehCacheCacheManager() {
			EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
			cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
			cmfb.setShared(true);
			return cmfb;
		}
	}

In non-web application, you need to shut down the Spring context manually, so that Ehcache got chance to shut down as well, otherwise Ehcache manager will hang there.

	public class App {
	
		public static void main(String[] args) {
	     
		    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

			// More code ...
		    
		    //shut down the Spring context.
		    ((ConfigurableApplicationContext)context).close();
		}
	}

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

[Watch video - server deployment and test scenario demo video](https://youtu.be/wvFBOVaBYes)