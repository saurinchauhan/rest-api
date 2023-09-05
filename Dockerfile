FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/rest-api-0.0.1.jar
ADD ${JAR_FILE} rest-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/rest-api-0.0.1.jar"]
