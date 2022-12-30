#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Pprod -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/target/uponchart-0.0.1-SNAPSHOT.jar uponchart.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","uponchart.jar"]
