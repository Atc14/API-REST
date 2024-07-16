FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /API-REST
WORKDIR /API-REST
RUN gradle build --no-daemon

FROM openjdk:17-alpine
COPY --from=build /API-REST/build/libs/*.jar api-rest.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-rest.jar"]
