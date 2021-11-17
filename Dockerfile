FROM openjdk:11 AS build

COPY pom.xml mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:11
WORKDIR bootcamp
COPY --from=build target/*.jar bootcamp-blog.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "bootcamp-blog.jar"]
