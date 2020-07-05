# Dockerfile
FROM openjdk:8-jre-alpine
MAINTAINER Javier Mozzicafreddo
WORKDIR /app
COPY ./target/exercise-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "exercise-0.0.1-SNAPSHOT.jar"]