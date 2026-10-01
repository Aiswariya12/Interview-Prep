# =========================================================================
# Stage 1: Build the Spring Boot JAR using Maven & Java 17
# =========================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven configuration and download dependencies first (Docker layer caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and package production JAR
COPY src ./src
RUN mvn clean package -DskipTests

# =========================================================================
# Stage 2: Lightweight Production Runtime Image
# =========================================================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy compiled JAR from build stage
COPY --from=build /app/target/interviewprep-api-1.0.0.jar app.jar

# Expose port (Render & Railway automatically inject PORT environment variable)
EXPOSE 8082

# Start Spring Boot application
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8082} -jar app.jar"]
