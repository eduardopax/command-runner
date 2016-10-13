FROM	eduardopax/debian8-oracle8jdk-maven-git

MAINTAINER Eduardo Lealdini Ramalho <eduardopax@gmail.com>

RUN		mkdir /app

WORKDIR	/app

RUN		git clone https://github.com/eduardopax/command-runner.git

WORKDIR	/app/command-runner

RUN 	mvn install -Dmaven.test.skip=true

RUN		cp target/$(ls target | grep command-runner | grep .war | grep -v original) /app

RUN		mkdir /app/scripts

RUN		cp src/main/resources/scripts/*.* /app/scripts

RUN		mkdir /app/config

RUN		cp src/main/resources/config/*.* /app/config

WORKDIR	/app

# Clean
RUN		apt-get clean all && \
		rm -rf /app/command-runner &&\
		rm -rf /root/.m2

EXPOSE	8081
		
ENTRYPOINT java -jar $(ls | grep command-runner)