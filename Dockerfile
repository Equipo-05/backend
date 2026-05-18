FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
EXPOSE 8086
COPY target/sasApp-0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]