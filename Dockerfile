#define base docker image
FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} linkSave.jar
ENTRYPOINT ["java","-jar","/linkSave.jar"]


