FROM gradle:3.4-jdk AS build
WORKDIR /api-rest
COPY ../api-rest
RUN gradle clean build


FROM openjdk:17-alpine
COPY --from=build api-rest/build/libs/api-rest-0.0.1-SNAPSHOT-plain.jar api-rest.jar
EXPOSE 8080
ENTRYPOINT["java","-jar","api-rest.jar"]