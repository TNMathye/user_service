FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy the pom.xml and src directory of the INNER user_service
COPY user_service/pom.xml user_service/pom.xml
COPY user_service/src user_service/src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre 
WORKDIR /app
COPY --from=builder /app/user_service/target/user_service-0.0.1-SNAPSHOT.jar user_service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user_service.jar"]
