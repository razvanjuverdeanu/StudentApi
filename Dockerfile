FROM openjdk:17-jdk-alpine
COPY target/StudentApi-0.0.1-SNAPSHOT.jar StudentApi.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar", "StudentApi.jar"]