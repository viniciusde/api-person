FROM eclipse-temurin:17-jre-alpine
VOLUME /api-person
ADD target/api-person-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]