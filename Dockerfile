FROM maven:3-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim-sid
COPY --from=build /target/m-delivery-service-0.0.1-SNAPSHOT.jar delivery.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","delivery.jar"]