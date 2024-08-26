# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the Docker image
COPY target/PaymentIntegration-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application runs on
EXPOSE 8821

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
