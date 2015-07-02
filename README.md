Prototype URL: [click here](http://ec2-54-174-149-143.compute-1.amazonaws.com/ADS/grahamtech/index.html)
# Graham Technologies (GT) | Agile Development Services (ADS) Prototype #

### Description: ###

The ADS project demonstrates our Agile capabilities which adhere to the U.S. Digital Services Playbook.  This document contains information on our Approach, Hosting Environment, Architecture and Install instructions.

### Approach: ###

GT utilized an iterative Agile Development Process (SCRUM) to design and implement a prototype to satisfy the requirements outlined in the ADS Statement of Work. Our team consisted of the following individuals: William Graham as the Technical Architect & Team Lead, Rodney Morris as a Backend Developer, Frank Goodman as a Frontend Web Developer and Brian Thompson as a DevOps Engineer.  

Technical Architect was responsible for leading a team of 3 Software Engineers to design and develop the ADS prototype.  Our Technical Architect controls prototype quality using 1) SourceForge - controls the allocation and prioritization of backlog items to be implementation and ultimately whether each sprint demo task item passes its Definition of Done and is allowed to be promoted or returned to the backlog for re-prioritization, and 2) GitHub - accepts or denies pull requests from the development team for moving code through the cycle of development to integration and/or master; and ultimately tags master for release.

Our team worked together to decompose traceable User Stories/requirements into executable development team tasks, which were assigned to different team members to satisfy the requirements of the prototype. More details on our Agile approach can be found in the AgileManagementApproach document under the supporting Documentation folder in GitHub.

### Hosting Environment Information & Open Source Software: ###

- Platform As a Service (PAAS):  Amazon Web Services Elastic Computing (EC2) – AWS was chose to provide a flexible hosting environment.  Our EC2 instance is a virtual machine which  can be saved to an ami, and the ami can be exported to various Virtual Machine formats such to enable our virtualized prototype to be moved to different containers. 
- Operating System Ubuntu 14.03 LTS - Open source operating system 
- [Apache Tomcat 7.0.52](http://ec2-54-174-149-143.compute-1.amazonaws.com/probe) psiprobe - Open source software hosting implementation
- Java 1.7 Open JDK 1.7.079-b14 - Java Runtime Environment.
- [Jenkins 1.6.17](http://ec2-54-174-149-143.compute-1.amazonaws.com:8080/login?from=%2F) – Continuous Integration to automate deployment and testing.
- Jenkins Login: 

	username: William_Graham / password: d#DU#N59V(Sc

- [Monit v 5.6](http://ec2-54-174-149-143.compute-1.amazonaws.com:2812/) - Monitors environment activities.
- Maven build tool v 3.2.5 - Builds and deploys projects. 
- [Git 1.9.1](https://github.com/GrahamTech/ADS) - Source Code Repository and Configuration Management.
- MySQL – Open Source Database to store data.

### Architecture: ###

Graham Technologies utilized a modern technology stack, which consisted of several open source technologies that were leveraged to build a layered architecture (Presentation, Service, Data).  The Presentation layer was implemented using AngularJS, JQuery and bootstrap.  The Service layer was implemented using the Spring Open Source Framework and Rest Based Web Services.  The Data Layer was implemented using the Hibernate open source framework and the Data Access pattern to store data into a MySQL database. 


#### REST API ####
Our WebServcies API can be found in GitHub in a file named REST_API.xlsx under the Supporting_Documents folder.

#### Unit Testing & Continuous Automation  ####
To ensure quality our team implemented using the Junit and Maven Surefire Frameworks.  All executed test reports can be reviewed on our Jenkins instance.  The URL to view the test results is http://ec2-52-4-97-82.compute-1.amazonaws.com:8080/job/ADS_prototype-integration/19/console.

Our team installed and configured Jenkins on our integration server.  Jenkins will detect any changes that are checked in to the integration branch within Git and generate a new build on the integration server and execute unit test to verify the quality of the latest build. The results of our latest build can be found at  URL:  http://ec2-52-4-97-82.compute-1.amazonaws.com:8080/job/ADS_prototype-integration/19/console

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
