FROM maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/m-delivery-service-0.0.1-SNAPSHOT.jar delivery.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","delivery.jar"]