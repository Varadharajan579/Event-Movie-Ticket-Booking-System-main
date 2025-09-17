# Use an official Java 17 runtime as base image
FROM eclipse-temurin:17-jdk-alpine

# Set app directory
WORKDIR /app

# Copy Maven build output JAR into container
COPY target/*.jar app.jar

# Expose port (Render expects 8080 for web services)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
