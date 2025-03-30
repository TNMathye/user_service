FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# Copy the Maven wrapper files and pom.xml
COPY user_service/pom.xml ./pom.xml
COPY user_service/.mvn/wrapper ./.mvn/wrapper
COPY user_service/mvnw ./
COPY user_service/mvnw.cmd ./
# Make the Maven wrapper executable
RUN chmod +x ./mvnw
# Copy the source code
COPY user_service/src ./src
# Build the application
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/user_service-0.0.1-SNAPSHOT.jar user_service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user_service.jar"]
