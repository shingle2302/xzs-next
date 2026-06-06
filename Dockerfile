FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY xzs-common/pom.xml ./xzs-common/
COPY xzs-domain/pom.xml ./xzs-domain/
COPY xzs-application/pom.xml ./xzs-application/
COPY xzs-adapter/pom.xml ./xzs-adapter/
COPY xzs-infrastructure/pom.xml ./xzs-infrastructure/
COPY xzs-start/pom.xml ./xzs-start/
RUN mvn dependency:go-offline -B
COPY . .
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/xzs-start/target/xzs-start-4.0.0.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
