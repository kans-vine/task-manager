# Read Me 
## Prerequisites before getting started with the project
* This project supports JDK 11. Have a Java 11 installed in your machine.
* Maven to build this project. The maven version used to manage the dependencies is 3.6.3

### Expectations:
* Write a Java service which offers two end points to create and list all the tasks
### What it offers:
* A secured rest api to create a task and fetch all the existing tasks 
 	## Add a new task:
	1) POST http://localhost:8082/api/tasks/ 
 
		 {
			"title":"Create a Task Manager Java Service",
			"description": "A service to handle the task manager",
			"status": "Not Started"
		 }
	2) POST http://localhost:8082/api/tasks/ 
 
		 {
			"title":"Create a Task Manager microservice",
			"description": "A service to handle the task manager",
			"status": "In Progress"
		 }
	3) POST http://localhost:8082/api/tasks/ 
 
		 {
			"title":"Create a Task Manager Service",
			"description": "A service to handle the task manager",
			"status": "Completed"
		 }
	## List all available tasks:
 	1) GET http://localhost:8082/api/tasks/
	
* Service monitoring endpoint to monitor the <a href="http://localhost:8082/actuator/health">health check</a> of the microservice
* API Documentation and Testing tool <a href="http://localhost:8082/swagger-ui/">Swagger</a> to vaildate the end points 
* Configured the in-memory persistance to persist the data in the microservice
* A basic jwt authentication to secure the microservice
				
# Getting Started
## Steps to run the project
### Steps to run the project using Terminal:
* Download the source code and place it in the preferred location
* Navigate through the command prompt to locate <b>pom.xml</b> available in the taskmanager directory once the codebase cloning is done
* Ensure maven and java environment variables are set and then execute the command <b>mvn clean install</b>
* Post packaging the application, start the microservice using the command <b>mvn spring-boot:run</b>
 ### (OR) Steps to run the project using IDE:
* Setup as an existing Maven project using any IDEs (STS, Eclipse). Note: STS has embedded Maven
* Right click on the project, click on Maven -> Update Project
* Then, Right Click on the project, click on Run As -> Maven Clean and Maven Install
* Then, Right Click on the project, click on Run As -> Spring Boot App
## Post launching steps:
* Once the service gets started, You can validate the service status <a href="http://localhost:8082/actuator">here</a>
* It will prompt for an username and password since we configured the basic authentication. 
	<ul>
		<li><b>USERNAME</b>: <b>user</b></li>
		<li><b>PASSWORD</b>: located in <b>logs/taskmanager.log</b> (OR) <b>printed in the console</b></li>
	</ul>		
* Use this <a href="http://localhost:8082/actuator/health"> link </a> to monitor the service health
* Use <a href="http://localhost:8082/swagger-ui/">Swagger</a> or Postman to submit the requests with Basic Auth as authorization Header




