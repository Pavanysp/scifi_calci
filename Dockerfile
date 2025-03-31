FROM openjdk:11

# Set the working directory inside  container
WORKDIR /app

# Copy the JAR file to container
COPY target/ScientificCalculator-1.0-SNAPSHOT.jar calculator.jar

# Run the application
CMD ["sh", "-c", "java -jar calculator.jar"]
