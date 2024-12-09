FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} tracking-service.jar
ENTRYPOINT ["java","-jar","/tracking-service.jar"]