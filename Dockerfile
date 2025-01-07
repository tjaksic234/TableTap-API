FROM openjdk:17-jdk-slim

WORKDIR /api

COPY target/TableTap-API-1.0-SNAPSHOT.jar /api/backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api/backend.jar"]