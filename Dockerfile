FROM	debian:jessie

MAINTAINER Eduardo Lealdini Ramalho <eduardopax@gmail.com>

# Always define userspace; security is going to be an ongoing issue with popularity of Docker.
# Beware of privilege escalation and do not trust arbitrary docker files blindly.
USER    root
ENV     DEBIAN_FRONTEND=noninteractive JAVA_HOME=/usr/lib/jvm/java-8-oracle

# Install Java 
RUN     echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list && \
        echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list && \
        echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections && \
        apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886 && \
		apt-get update && \
        apt-get install -y --force-yes --no-install-recommends ca-certificates oracle-java8-installer

RUN 	apt-get install -y git

RUN 	apt-get install -y maven

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
		rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
		rm -rf /var/cache/oracle-jdk8-installer && \
		rm -rf /app/command-runner &&\
		rm -rf /root/.m2

EXPOSE	8081
		
ENTRYPOINT java -jar $(ls | grep command-runner)