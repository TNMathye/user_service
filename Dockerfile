FROM openjdk:21-slim
COPY target/*.jar user_service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user_service.jar"]