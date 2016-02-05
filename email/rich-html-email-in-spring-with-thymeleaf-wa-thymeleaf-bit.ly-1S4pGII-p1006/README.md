# Rich HTML Email in Spring with Thymeleaf

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**  
**[Important code blocks](#important-code-blocks)**  
**[System requirements](#system-requirements)**  
**[Deployment and Tests](#deployment-and-tests)**  

## Resource location
- Resource - http://www.thymeleaf.org/doc/articles/springmail.html

## Overview
How to use Thymeleaf templates for composing email messages of several kinds, and we will integrate this with Spring’s email utilities in order to configure a simple but powerful email system. 

Our example application will be sending four types of emails:

1. Simple HTML (with internationalized greeting).
1. HTML text with an attachment.
1. HTML text with an inline image.
1. HTML text edited by the user.

## Important code blocks

Configure a Mail Sender object in your Spring configuration

	@Bean
	public JavaMailSender mailSender() throws IOException {
	    Properties properties = configProperties();
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(properties.getProperty("mail.server.host"));
	    mailSender.setPort(Integer.parseInt(properties.getProperty("mail.server.port")));
	    mailSender.setProtocol(properties.getProperty("mail.server.protocol"));
	    mailSender.setUsername(properties.getProperty("mail.server.username"));
	    mailSender.setPassword(properties.getProperty("mail.server.password"));
	    mailSender.setJavaMailProperties(javaMailProperties());
	    return mailSender;
	}
	 
	private Properties configProperties() throws IOException {
	    Properties properties = new Properties();
	    properties.load(new ClassPathResource("configuration.properties").getInputStream());
	    return properties;
	}
	 
	private Properties javaMailProperties() throws IOException {
	    Properties properties = new Properties();
	    properties.load(new ClassPathResource("javamail.properties").getInputStream());
	    return properties;
	}

Thymeleaf - Template Resolver for email templates.

	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addTemplateResolver(emailTemplateResolver());
	    templateEngine.addTemplateResolver(webTemplateResolver());
	    return templateEngine;
	}

	private TemplateResolver emailTemplateResolver() {
	    TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("/mail/");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setOrder(1);
	    return templateResolver;
	}

The service class. Use `MimeMessageHelper` to ease the creation of email messages

	public void sendMailWithInline(
		final String recipientName, final String recipientEmail, final String imageResourceName,
		final byte[] imageBytes, final String imageContentType, final Locale locale)
		throws MessagingException {

		// More code ...
		
		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setFrom("sender@example.com");
		message.setTo("recipient@example.com");
		message.setSubject("This is the message subject");
		message.setText("This is the message body");

		// Send mail
  		this.mailSender.send(mimeMessage);

	}
	
## System requirements

The case study was developed using the following:

- Windows 10, 64-bit
- IntelliJ Idea 15.0 Ultimate
- JDK 1.7.0_45
- Apache Maven 3.0.3
- Spring 3.1.0.RELEASE
- Thymeleaf 2.1.3 RELEASE
- Java Mail 1.4.4

## Deployment and Tests

### Configure SMTP server

Modify the following variable values on `src/main/resources/configuration.properties` using gmail credentials

- mail.server.username
- mail.server.password

### Start Wildfly
1. Open a command line and navigate to the root of the Tomcat directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/startup.sh
        For Windows: WILDFLY_HOME\bin\startup.bat

### Build and Deploy the application
1. Make sure you have started the Tomcat Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and deploy the archive:

        mvn wildfly:deploy  

4. This will deploy `target/springmail.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Tomcat Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

### Deployment and test scenario demo video

[Watch video - server deployment and test scenario demo video](https://youtu.be/GiQbpesEoqc)