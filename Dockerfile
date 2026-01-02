FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x gradlew && ./gradlew clean bootJar --no-daemon

FROM --platform=linux/amd64 eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
