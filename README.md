# **Command Runner**
Run shell commands through a Web Server in Linux machines.

**1.Introduction**

Create scripts and link with a configuration file, start the application and scripts are ready to run through the web application.

**2.Technology**

Web Server using Spring Boot, AngularJS and Bootstrap.

Screen optimized to Mobile First.

**3.How it works**

The program uses the Spring Boot as the java framework, you can change Spring Boot configurations defined by Spring. For example, is possible to change the http port default 8081 to another port.

The program load the file command-runner.yml when it starts and the commands configured will be accessible through the web site.

All commands will run a shell script associated in the command-runner.yml.

**4.Installing**

Compile the project using __mvn clean install__ .

Copy .jar file to folder that will be used to run the program.

Create the folders /script and /config at the same folder.

Create the file configuration command-runner.yml inside /config.

Create the shell files inside /scripts.

Start the application __java jar command-runner-VERSION.jar__ (It is possible to use Spring Boot parameters).

****IMPORTANT - The application will give execution permission in all files .sh, it is important to start the program with an user configurated as root.****

**5.Docker container**

Docker container to test the code: https://hub.docker.com/r/eduardopax/command-runner/

docker pull eduardopax/command-runner
