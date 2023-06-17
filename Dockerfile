# Base image
FROM eclipse-temurin

# Update aptitude with new repo
RUN apt-get update

# Install software
RUN apt-get install -y git

# Set the working directory
WORKDIR /app

# Clone the Spring Boot app from GitHub
RUN git clone https://github.com/NeverGiveUpLtda/Turistae-API.git

# Change to the cloned app directory
WORKDIR /app/Turistae-API
RUN chmod +x mvnw

# Set the entry point to run the app using the Maven wrapper
ENTRYPOINT ["./mvnw", "spring-boot:run"]
