FROM ubuntu:latest
LABEL authors="Ann-Jacqueline, Emilie"

FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
LABEL org.name="Ann-Jacqueline"

FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /home/gradle/src/build/libs/backend-webtech-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
