FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY src/main/resources/wallet /app/wallet

RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "target/novastep-0.0.1-SNAPSHOT.jar"]