FROM openjdk:21-ea-1-slim
LABEL authors="Saulo"

WORKDIR /app

COPY target/release_manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", "-jar", "app.jar"]