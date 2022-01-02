#define base docker image
FROM openjdk:8
ADD target/linkSave-0.0.1-SNAPSHOT.jar linkSave.jar
ENTRYPOINT ["java","-jar","linkSave.jar"]


