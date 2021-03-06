# Dockerfile for sample service using embedded tomcat server

FROM centos:centos7
MAINTAINER debugroom

RUN yum install -y \
    java-1.8.0-openjdk \
    java-1.8.0-openjdk-devel \
    wget tar iproute git

RUN rm -f /etc/rpm/macros.image-language-conf && \
    sed -i '/^override_install_langs=/d' /etc/yum.conf && \
    yum -y reinstall glibc-common && \
    yum clean all

ENV LANG="ja_JP.UTF-8" \
    LANGUAGE="ja_JP:ja" \
    LC_ALL="ja_JP.UTF-8"

RUN wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
RUN sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
RUN yum install -y apache-maven
ENV JAVA_HOME /etc/alternatives/jre
RUN git clone https://github.com/debugroom/mynavi-sample-continuous-integration.git /usr/local/mynavi-sample-continuous-integration
RUN mvn install -f /usr/local/mynavi-sample-continuous-integration/common/pom.xml
RUN mvn package -f /usr/local/mynavi-sample-continuous-integration/backend-for-frontend/pom.xml -Dspring.profiles.active="production"
RUN cp /etc/localtime /etc/localtime.org
RUN ln -sf  /usr/share/zoneinfo/Asia/Tokyo /etc/localtime

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=production /usr/local/mynavi-sample-continuous-integration/backend-for-frontend/target/mynavi-sample-continuous-integration-backend-for-frontend-0.0.1-SNAPSHOT.jar