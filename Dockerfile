FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# Adjust paths to point to the correct location
COPY user_service/user_service/pom.xml ./pom.xml
COPY user_service/user_service/src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre 
WORKDIR /app
COPY --from=builder /app/target/user_service-0.0.1-SNAPSHOT.jar user_service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user_service.jar"]
