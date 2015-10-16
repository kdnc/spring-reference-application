# Domain object level permissions

## Table of Contents
**[Resource location](#resource-location)**  
**[Overview](#overview)**
**[System requirements](#system-requirements)**
**[Important code blocks](#important-code-blocks)**  
**[Project notes](#project-notes)**  
**[Server deployment](#server-deployment)**  
**[Tests](#tests)**  

## Resource location
* Resource - Pluralsight - Spring Security Fundamentals - 08. Enabling Security with Expressions

## Overview
How to enable domain object level custom permissions. 
	
- Able to enable per object permissions
	- A level deeper than ROLE

## System requirements
This project was successfully built using Java 7.0 (Java SDK 1.7), Maven 3.0.3 and Wildfly 8.1.0.Final.

## Important code blocks

1. sql/fitnessTracker.sql
	- permissions table to hold custom permissions.

			CREATE TABLE `permissions` (
			  `username` varchar(50) NOT NULL,
			  `target` varchar(50) NOT NULL,
			  `permission` varchar(50) NOT NULL,
			  UNIQUE KEY `ix_perm_username` (`username`,`target`,`permission`),
			  CONSTRAINT `fk_permissions_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
			) ENGINE=InnoDB DEFAULT CHARSET=utf8;

			INSERT INTO `permissions` VALUES ('admin','com.pluralsight.model.Goal','createGoal');

2. src/main/java/com/pluralsight/security/FitnessPermissionEvaluator.java
	- Custom Permission Evaluator 
		- FitnessPermissionEvaluator
	- Interface with two methods:
		- hasPermission(Authentication auth, Object targetObj, Object permission)
		- hasPermission(Authentication auth, Serializable id, String type, Object 
permission)
	- Every implementation will be different
		- Inject whatever you need to the evaluator

				public class FitnessPermissionEvaluator implements PermissionEvaluator {
					private DataSource datasource;
				
					public DataSource getDatasource() {
						return datasource;
					}
				
					public void setDatasource(DataSource datasource) {
						this.datasource = datasource;
					}
				
					public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
						JdbcTemplate template = new JdbcTemplate(datasource);
						
						Object [] args = {((User)auth.getPrincipal()).getUsername(), 
								targetDomainObject.getClass().getName(), 
								permission.toString()};
						
						int count = template.queryForObject("select count(*) from permissions p where " +
								"p.username = ? and p.target = ? and p.permission = ?", args, Integer.class);
						
						if(count == 1) {
							return true;
						} else {
							return false;
						}
					}
				
					public boolean hasPermission(Authentication arg0, Serializable id,
							String type, Object permission) {
						return false;
					}
				}

3. src/main/java/com/pluralsight/controller/GoalController.java
	- hasPermission(#goal, ‘createGoal’) 
		- Tied to an object, #goal
		- Permission, createGoal
					
				@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#goal, 'createGoal')")
				public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) { }

4. src/main/webapp/WEB-INF/config/security-config.xml
	- <security:expression-handler ref=“fitnessExpressionHandler” />
		
			<security:global-method-security pre-post-annotations="enabled">
				<security:expression-handler ref="fitnessExpressionHandler"/>
			</security:global-method-security>
			
			<bean id="fitnessExpressionHandler" 
				class="org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler">
				<property name="permissionEvaluator">
					<bean id="permissionEvaluator" class="com.pluralsight.security.FitnessPermissionEvaluator">
						<property name="datasource" ref="dataSource" />
					</bean>
				</property>
			</bean>
	
## Project notes

Additional project notes can be found in exercise-files/8-springsec-fundamentals-slides.pdf file in the video tutorial.

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

4. This will deploy `target/domain-object-level-permissions.war` to the running instance of the server.

### Undeploy the Archive
1. Make sure you have started the Wildfly Server as described above.
2. Open a command line and navigate to the root directory of this example.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Tests

### Test scenario 1 - "admin" user should be able to add a goal

1. Navigate to the following url.       
<http://localhost:8080/domain-object-level-permissions/>
2. You should be redirected to the <http://localhost:8080/domain-object-level-permissions/login.html> page.
3. Login to the application with following credentials:
	* username - admin
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Click on the "Add Goal" button.
6. You should see <http://localhost:8080/domain-object-level-permissions/addGoal.html> page.
7. Click on "Enter Goal Minutes" button.
8. You should be redirected to the welcome page <http://localhost:8080/domain-object-level-permissions/>.
9. Check the server log and observe the following log.

		10:34:06,880 INFO  [stdout] (default task-28) result has errors: false
		10:34:06,881 INFO  [stdout] (default task-28) Goal set: 10

### Test scenario 2 - "admin2" user should NOT be able to add a goal

1. Navigate to the following url.       
<http://localhost:8080/domain-object-level-permissions/>
2. You should be redirected to the <http://localhost:8080/domain-object-level-permissions/login.html> page.
3. Login to the application with following credentials:
	* username - admin2
	* password - 123
4. You should be able to successfully login and the welcome page should be displayed.
5. Click on the "Add Goal" button.
6. You should see <http://localhost:8080/domain-object-level-permissions/addGoal.html> page.
7. Click on "Enter Goal Minutes" button.
8. Error should be displayed. Observe the following error.
	
		Request method 'POST' not supported