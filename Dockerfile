FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/API_REST_Survey_IT-0.0.1-SNAPSHOT.jar /app/api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/api.jar"]