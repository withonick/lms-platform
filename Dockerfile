FROM openjdk:17-oracle
MAINTAINER otkirbek
COPY ../docker/back.jar spring-backend.jar
ENTRYPOINT ["java", "-jar", "spring-backend.jar"]