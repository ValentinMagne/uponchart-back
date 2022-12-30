FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
# mvn package -Dspring.profiles.active=dev
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# docker build -t vmagne/uponchart .
# docker run -p 8080:8080 vmagne/uponchart
