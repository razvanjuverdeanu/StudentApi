FROM maven:3.8.3-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim-sid
COPY --from=build /target/StudentApi-0.0.1-SNAPSHOT.jar StudentApi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "StudentApi.jar"]