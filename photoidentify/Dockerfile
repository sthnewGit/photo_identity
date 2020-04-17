FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD target/photoidentify.jar app.jar
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=9080","-jar","/app.jar" ]


