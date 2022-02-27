FROM gradle:7.4-jdk17 as builder

WORKDIR /usr/local/app
COPY ./ /usr/local/app/

RUN gradle build -x test

FROM openjdk:17.0.2-jdk

ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /usr/local/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080
