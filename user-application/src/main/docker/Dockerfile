FROM frolvlad/alpine-oraclejre8:slim
VOLUME /tmp
ADD user-application-*.jar user-application.jar
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-application.jar", "--spring.profiles.active=dev"]
