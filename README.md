# UPON CHART

## INSTALL

### LOCALLY

* Simply run with 

```shell
mvn spring-boot:run -Pdev
```

* or create a package

```shell
mvn package -Dspring.profiles.active=dev
java -jar -Dspring.profiles.active=dev target/uponchart-0.0.1-SNAPSHOT.jar
```

### DOCKER 

* Warning : prod config

```shell
docker build -t vmagne/uponchart .
docker run -p 8080:8080 --rm -it vmagne/uponchart:latest
```
