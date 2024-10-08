FROM openjdk:17-jdk-slim

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=local

ENTRYPOINT ["java","-jar","/app.jar"]