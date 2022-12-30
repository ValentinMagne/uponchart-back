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
ENTRYPOINT ["java","-jar","uponchart.jar"]
# docker build -t vmagne/uponchart .
# docker run -p 8080:8080 --rm -it vmagne/uponchart:latest
