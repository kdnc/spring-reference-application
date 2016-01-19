# Bean Post Processor

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 02 The Life of a Bean - 09 Teach your beans new tricks with Bean(Factory)PostProcessors

## Overview
- Simple BPP that automatically adds logging functionality to all objects that implement  `@Timed` custom annonation

## Important code blocks

implements `BeanPostProcessor`

	public class MethodTimeLoggingBeanPostProcessor implements BeanPostProcessor

override `postProcessAfterInitialization` method

	@Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new TimeLoggingMethodInterceptor());
        factory.setTarget(bean);
        return (Object) factory.getProxy();
    }

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE
