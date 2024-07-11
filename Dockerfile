FROM eclipse-temurin:17-jre-alpine
WORKDIR /aplikacja
COPY ./target/*.jar apka.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "apka.jar"]

