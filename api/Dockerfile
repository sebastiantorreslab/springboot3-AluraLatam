FROM openjdk:17
WORKDIR /app
COPY target/api-0.0.1-SNAPSHOT.jar java.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","java.jar"]