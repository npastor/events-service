FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/eventService-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/app.jar"]