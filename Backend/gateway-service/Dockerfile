# Etapa de construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/app/gateway-service.jar"]