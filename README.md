# UPON CHART

## INSTALL

### LOCALLY

* First start PostgreSQL with

```shell
docker-compose -f src/main/docker/docker-compose-dev.yml up -d
```

* Then simply start the app with 

```shell
mvn spring-boot:run
# or 
./mvnw
```

* or create a package before running the app

```shell
mvn package -Dspring.profiles.active=dev
java -jar -Dspring.profiles.active=dev target/uponchart-0.0.1-SNAPSHOT.jar
```

### DOCKER 

* Warning : prod config
* You have to configure a `.env` file with db variables : 
  * JDBC_DATABASE_USERNAME
  * JDBC_DATABASE_PASSWORD

```shell
docker build -t vmagne/uponchart .
docker run -p 8080:8080 --env-file ./.env --rm -it vmagne/uponchart:latest
```
