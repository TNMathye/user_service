FROM maven:3.9.9-openjdk:21-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/user_service-0.0.1-SNAPSHOT.jar user_service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user_service.jar"]