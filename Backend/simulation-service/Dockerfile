FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} simulation-service.jar
ENTRYPOINT ["java","-jar","/simulation-service.jar"]