FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} totalcost-service.jar
ENTRYPOINT ["java","-jar","/totalcost-service.jar"]