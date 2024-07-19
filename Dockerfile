# Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# OpenJDK image to run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Recruitment_MMIL-0.0.1-SNAPSHOT.jar ./Recruitment_MMIL-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Recruitment_MMIL-0.0.1-SNAPSHOT.jar"]
