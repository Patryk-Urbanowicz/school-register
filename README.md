# School register (name subject to change)

School register app written using Java Spring Boot framework.

## Technology stack:
- PostgreSQL 14.2
- Java 11
- Spring Boot 12
- Docker and Docker Compose

## Ports

PostgreSQL is running on port 5432 and Spring Boot is on 8080.
## How to run

### Using docker-compose

```sh
        #make sure docker service is running on your system
        docker-compose up
```

it is advised to first run `docker-compose build` to first build the containers and make sure there are no errors.


If for some reason `docker ps` doesn't show port forwarding for Spring app then run `docker-compose down app && docker-compose up app`.
### Running localy

#### PostgreSQL
TODO

#### Spring Boot
Make sure that the `application.properties` match your database config. 
Run 
```sh
mvn -B dependency:resolve dependency:resolve-plugins && \
mvn package

java -jar $(ls /path/to/project/target/*.jar)
```

or just open the project in your preferred IDE and run it.

## Authors
[Kamil Kocieniewski](https://github.com/KamilKoc-code)  
[Szymon Orzechowski](https://github.com/szymonorz)  
[Patryk Urbanowicz](https://github.com/Patryk-Urbanowicz)

## For developers

For now it is advised to *NOT* run Spring Boot app inside the Docker container while it is being developed. The reason being the container takes some time to rebuild itself.
As for now only run the PostgreSQL container and launch Spring Boot localy on your own machine. Due to this there are some changes that need to be made inside `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/schoolregister
spring.datasource.username=postgres
spring.datasource.password=postgres
```

This is how the datasource properties should look like. For production this will be changed. For development leave it as is or make sure it matches your `.env` and/or your PostgreSQL config.


