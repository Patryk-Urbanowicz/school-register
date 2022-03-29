### RUN image
FROM alpine:3.15.2 as runtime
ENV APP_HOME /app

WORKDIR $APP_HOME
COPY pom.xml $APP_HOME
COPY src $APP_HOME/src

RUN apk update \
    && apk upgrade \
    && apk add --update openjdk11 maven \
    && rm -rf /var/cache/apk/* \
    && mvn -B dependency:resolve dependency:resolve-plugins \
    && mvn package && addgroup -S spring && adduser -S spring -G spring \
    && cp target/*.jar spring_app.jar

USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./spring_app.jar"]
