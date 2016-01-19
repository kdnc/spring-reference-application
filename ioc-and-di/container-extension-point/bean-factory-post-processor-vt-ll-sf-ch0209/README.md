# Bean Factory Post Processor

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[Important code blocks](#important-code-blocks)**
**[System requirements](#system-requirements)** 

## Resource location
- Resource - [LiveLessons] Spring Framework (Aug 27, 2013) - 02 The Life of a Bean - 09 Teach your beans new tricks with Bean(Factory)PostProcessors

## Overview
- This simply ensures that all the required beans are defined in your application context
 installs `AnnotationAwareAspectJAutoProxyCreator` and  `tutorial.aop.MethodTimeLoggingAspect`

## Important code blocks

implements `BeanFactoryPostProcessor`

	public class SoxComplianceSuite implements BeanFactoryPostProcessor

override `postProcessBeanFactory` method

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException

register beanFactory with `AnnotationAwareAspectJAutoProxyCreator` and `MethodTimeLoggingAspect`

 	/**
     * <bean class = "...AnnotationAwareAspectJAutoProxyCreator"> </bean>
     */
    if (!definitionExists(AnnotationAwareAspectJAutoProxyCreator.class, beanFactory))
        BeanDefinitionReaderUtils.registerWithGeneratedName(
                new RootBeanDefinition(AnnotationAwareAspectJAutoProxyCreator.class), registry);

    /**
     * <bean class = "...MethodTimeLoggingAspect"> </bean>
     */
    if (!definitionExists(MethodTimeLoggingAspect.class, beanFactory))
        BeanDefinitionReaderUtils.registerWithGeneratedName(new RootBeanDefinition(MethodTimeLoggingAspect.class), registry);

Define the aspect to call

	@Aspect
	public class MethodTimeLoggingAspect {
		@Around("@annotation(tutorial.Timed)")
		public Object time(ProceedingJoinPoint invocation) throws Throwable {
		}
	}

## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea
- JDK 1.7.0_45
- Maven 3.0.3
- Spring 3.2.2.RELEASE
