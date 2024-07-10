# Build the application
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY . .
# Make the Gradle Wrapper executable
RUN chmod +x /app/gradlew
# Build the application
RUN ./gradlew clean build --no-daemon

# Create the final image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/url-shortener-0.0.1-SNAPSHOT.jar /app/url-shortener.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/url-shortener.jar"]