FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY server/build/libs/student-service-0.0.1.jar StudentService.jar

ENTRYPOINT ["java", "-jar", "StudentService.jar"]

