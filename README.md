Prototype URL: [click here](http://ec2-52-4-97-82.compute-1.amazonaws.com/ADS/grahamtech/index.html)
# Graham Technologies (GT) | Agile Development Services (ADS) Prototype #

### Description: ###

The ADS project demonstrates our Agile capabilities which adhere to the U.S. Digital Services Playbook.  This document contains information on our Approach, Hosting Environment, Architecture and Install instructions.

### Approach: ###

GT utilized an iterative Agile Development Process (SCRUM) to design and implement a prototype to satisfy the requirements outlined in the ADS Statement of Work. Our team consisted of the following individuals: William Graham as the Technical Architect & Team Lead, Rodney Morris as a Backend Developer, Frank Goodman as a Frontend Web Developer and Brian Thompson as a DevOps Engineer.  

Our team worked together to decompose traceable User Stories/requirements into executable development team tasks, which were assigned to different team members to satisfy the requirements of the prototype. Below are the high-level steps within our process executed to implement the system:

1.	Upload System requirements to TeamForge. Decompose the requirements into Themes/Epics, User Stories, and Tasks.  Please see AgileManagementApproach.docx document for a list of all System Requirements.
2.	Created a backlog that contained all of the Tasks required to satisfy the requirements.  Please see ADSTaskList.xslx for the tasks identified for the product backlog.
3.	Host daily stand-up meetings to discuss progress of assigned tasks. 
4.	Worked in 3-day sprints to complete tasks identified in product backlog.
5.	Deploy source code after each sprint and demonstrate functionality.

### Hosting Environment Information & Software: ###

- Platform As a Service (PAAS):  Amazon Web Services Elastic Computing (EC2) – flexible hosting environment.
- Operating System Ubuntu 14.03 LTS - Open source operating system 
- [Apache Tomcat 7.0.52](http://ec2-52-4-97-82.compute-1.amazonaws.com/probe) psiprobe - Open source software hosting implementation
- Java 1.7 Open JDK 1.7.079-b14 - Java Runtime Environment.
- [Jenkins 1.6.17](http://ec2-52-4-97-82.compute-1.amazonaws.com:8080/login?from=%2F) – Continuous Integration to automate deployment and testing.
- Jenkins Login: 

	username: William_Graham / password: d#DU#N59V(Sc

- [Monit v 5.6](http://ec2-52-4-97-82.compute-1.amazonaws.com:2812/) - Monitors environment activities.
- Maven build tool v 3.2.5 - Builds and deploys projects. 
- [Git 1.9.1](https://github.com/GrahamTech/ADS) - Source Code Repository.
- MySQL – Open Source Database to process data.

### Architecture: ###

Graham Technologies utilized a modern technology stack, which consisted of several open source technologies that were leveraged to build a layered architecture (Presentation, Service, Data).  The Presentation layer was implemented using AngularJS, JQuery and bootstrap.  The Service layer was implemented using the Spring Open Source Framework and Rest Based Web Services.  The Data Layer was implemented using the Hibernate open source framework and the Data Access pattern to store data into a MySQL database. 

#### REST API ####

Within the ADS URL Context --

Url           |Verb          | Description
--------------|------------- | -------------
gt/get/drug/events/and/store/apikey?rowLimit={id} |	GET	| 	Retrieves Adverse Drug Events from an external REST provider, parses the returned JSON, stores a subset of the data into the ADS data store, and then returns a list of events stored within the ADS data store in JSON form 
gt/read/db/events |	GET	| Retrieves all events from the ADS data store and returns it via JSON
gt/read/db/event?event_id={id} |	GET	| Uses the {id} number value passed on the request URL into ADS from the requester to fetch and return the specified event from the ADS data store via JSON
gt/delete/db/event?event_id={id} |	DELETE	| Uses the {id} number value passed on the request URL into ADS from the requester to delete an event from the ADS data store. A Status Message JSON is returned to the requestor with a code indicating success or failure, alongside message details
gt/create/db/event |	POST	| Creates an event in the ADS data store using the contents passed to ADS within the request body
gt/update/db/event |	POST	| Updates an event in the ADS data store using the contents passed to ADS within the request body

### Installation Instructions: ###

1.	Install Java – sudo apt-get install openjdk-7-jdk
2.	Install Tomcat - sudo apt-get install tomcat7-user
3.	Install additional Tomcat functionality - sudo tomcat7-instance-create my-instance
4.	Start Tomcat - sudo /etc/init.d/tomcat-dev (start|stop|restart)
5.	Download Maven to /opt directory - 'sudo wget http://mirror.nexcess.net/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz'
6.	Unpack maven 'sudo tar -xvf  apache-maven-3.2.5-bin.tar.gz' and then link the mvn executable to the /usr/local/bin' directory
7.	Pull Source code GitHub(Master) https://github.com/GrahamTech/ADS
8.	Locate the pom.xml file in root directory and update the {build folder}variable to point to the tomcat home directory:
  a.	<webappDirectory.location>{build folder}/webapp</webappDirectory.location>
  b.	<outputDirectory>{build folder}/target</outputDirectory>
9.	Install and Create a MySQL database:
  a.	Download and Install MySQL
  b.	Start MySQL Database Server
  c.	<mysql>: create database adsdb;
  d.	SET PASSWORD FOR 'root'@'localhost'=PASSWORD('$Password001');
  e.	<mysql>SOURCE {path to ads.sql}/ads.sql
10.	Build and Deploy - mvn  -DskipTests=true package
