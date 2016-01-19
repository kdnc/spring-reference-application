# Around Advice

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 02 The Life of a Bean - 10 Teach your beans new tricks with AOP

## Overview
- Log the time on methods which are annotated `@Timed` annotation

## Important code blocks

@EnableAspectJAutoProxy

	@Configuration 
	@EnableAspectJAutoProxy
	public class AnimalFarmConfig

Define aspect as a bean in configuration
	
	@Bean
    public MethodTimeLoggingAspect aspect() {
        return new MethodTimeLoggingAspect();
    }
	

Define `Aspect`

	@Aspect
	public class MethodTimeLoggingAspect {

		@Around("@annotation(tutorial.Timed)")
		public Object time(ProceedingJoinPoint invocation) throws Throwable {

			System.out.println(invocation.getSignature().getName() + "()");
			Object result = invocation.proceed();
		}
	}


Define the `@Timed` annotation on `Cat` class's `meow` method

	@Timed
    public void meow() {
        System.out.println("meow...");
    }

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE
