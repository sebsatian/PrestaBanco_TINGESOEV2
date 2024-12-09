FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} request-service.jar
ENTRYPOINT ["java","-jar","/request-service.jar"]