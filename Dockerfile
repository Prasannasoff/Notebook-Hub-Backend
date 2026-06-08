# Use an official OpenJDK 21 runtime as the base image
FROM eclipse-temurin:21-jdk
# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/notebook-hub-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (same as your Spring Boot server.port)
EXPOSE 9092

# Command to run the application
CMD ["java", "-jar", "app.jar"]
