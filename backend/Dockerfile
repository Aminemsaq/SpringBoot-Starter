# Build Stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Cache dependencies
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn -q clean package -DskipTests

# Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy compiled jar from build stage
COPY --from=build /app/target/backend.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]