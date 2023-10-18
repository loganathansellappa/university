## Use a base image with Java and Maven
FROM jelastic/maven:3.9.5-openjdk-22.ea-b18 AS builder

ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILE



## Set the working directory
WORKDIR /app/university

## Copy the source code to the container
COPY . /app/university
## Build the application
RUN mvn clean install -DskipTests
## Move buuild to root directory
RUN mv target/*.jar university.jar

FROM jelastic/maven:3.9.5-openjdk-22.ea-b18
# Set the working directory
WORKDIR /app
# Copy the JAR file built in the previous stage
COPY --from=builder /app/university/university.jar ./
# Expose the port your application runs on
EXPOSE 8080
ENTRYPOINT ["java","-jar","university.jar"]

## build command
##docker build --build-arg SPRING_PROFILE=prod -t my-spring-app:1.1.0 .
