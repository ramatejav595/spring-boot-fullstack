FROM eclipse-temurin:17
LABEL authors="ramateja"

WORKDIR app/

COPY target/customerservice-0.0.1-SNAPSHOT.jar /app/customerservice.jar

ENTRYPOINT ["java", "-jar", "customerservice.jar"]