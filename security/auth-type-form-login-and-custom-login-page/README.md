# Auth Type "form-login" and display a custom login page

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 07. Customizing Spring Security

## Overview
How to use auth type "form-login" in Spring security and displaying a custom login form.

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. src/main/webapp/WEB-INF/config/security-config.xml - intercept-url element to allow access to login page for all users

2. src/main/webapp/WEB-INF/config/security-config.xml - form-login element to define custom login page, logout page, login failed page and auth type

		<http auto-config="true">
			<intercept-url pattern="/login.html" access="IS_AUTHENTICATED_ANONYMOUSLY" />
			<intercept-url pattern="/loginFailed.html" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
			<intercept-url pattern="/logout.html" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
			<!-- <intercept-url pattern="/403.html" access="IS_AUTHENTICATED_ANONYMOUSLY"/>  -->
			<intercept-url pattern="/**" access="ROLE_USER" />
			<form-login login-page="/login.html" authentication-failure-url="/loginFailed.html"/>
			<logout logout-success-url="/logout.html"/>
			<access-denied-handler error-page="/403.html"/>
		</http>

3. src/main/java/com/pluralsight/controller/LoginController.java
	- Directs to our login.jsp 
	- Allows us to return more data to our index page

			@RequestMapping(value="/login", method=RequestMethod.GET)
			public String login(ModelMap model) {
				System.out.println("In the login method");
				
				return "login";
			}
			
			@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
			public String loginFailed(ModelMap model) {
				System.out.println("Login Failed");
				
				model.addAttribute("error", "true");
				return "login";
			}
			
			@RequestMapping(value="/logout", method=RequestMethod.GET)
			public String logout(ModelMap model) {
				return "logout";
			}
			
			@RequestMapping(value="/403", method=RequestMethod.GET)
			public String error403(ModelMap model) {
				return "403";
			}

4. src/main/webapp/WEB-INF/jsp/login.jsp
	- Hosted through Spring MVC LoginController

			<form action="j_spring_security_check" name="f" method="post">
				<table>
					<tr> 
						<td>User:</td>
						<td><input type="text" name="j_username" value=""></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="j_password" ></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="Submit" value="Submit"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="reset" name="reset" > </td>
					</tr>	
				</table>
			</form>	

5. src/main/webapp/index.jsp - Link to logout from the application

		<a class="btn btn-warning" href="j_spring_security_logout">
        	Logout >>
        </a>

## Project notes

Additional project notes can be found in exercise-files/7-springsec-fundamentals-slides.pdf file in the video tutorial.

## Server deployment

### Database import

1. Open a command line and navigate to the sql folder in the project.
		
		mysql -uroot -p123

2. In the mysql console import the .sql file
		
		source fitnessTracker.sql

### Start Wildfly
1. Open a command line and navigate to the root of the Wildfly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/startup.sh
        For Windows: WILDFLY_HOME\bin\startup.bat

### Build and Deploy the application
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. Type this command to build and deploy the archive:

        mvn wildfly:deploy  

4. This will deploy `target/auth-type-form-login-and-custom-login-page.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - Login to the system

1. Navigate to the following url.       
<http://localhost:8080/auth-type-form-login-and-custom-login-page/>
2. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/login.html> page.
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Clear the cookies.
6. Navigate to the following url.       
<http://localhost:8080/auth-type-form-login-and-custom-login-page/>
7. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/login.html> page.
8. Login to the application with following credentials:
	* username - admin
	* password - 123
9. You should be able to successfully login and the welcome page should be displayed.

### Test scenario 2 - Failed login should display the error message

1. Navigate to the following url.       
<http://localhost:8080/auth-type-form-login-and-custom-login-page/>
2. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/login.html> page.
3. Enter bad credentails to the login form
	* username - userbad
	* password - 123bad
4. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/loginFailed.html> page and observe the following output.
		
		Your login was unsuccessful. 
		Caused: Bad credentials

### Test scenario 3 - Logout from the application

1. Navigate to the following url.       
<http://localhost:8080/auth-type-form-login-and-custom-login-page/>
2. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/login.html> page.
3. Login to the application with following credentials:
	* username - user
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Click on the "Logout >>" button.
4. You should be redirected to the <http://localhost:8080/auth-type-form-login-and-custom-login-page/logout.html> page and observe the following output.
		
		Fitness Tracker Custom Logout Page

		You have been logged out thanks for using our app.