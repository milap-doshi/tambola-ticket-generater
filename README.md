# tambola-ticket-generater

A Spring Boot service to generate Tambola (bingo/housie) tickets. Provides a REST API for programmatic ticket generation.

## Features
- Generate valid Tambola tickets with configurable count and layout
- REST API for single and bulk ticket generation
- Gradle build with unit tests
- macOS-ready setup

## Tech Stack
- Java 17+
- Spring Boot
- Gradle

## Prerequisites (macOS)
- Java 17 or newer: `java -version`
- Gradle wrapper included (use `./gradlew`)
- Internet access for dependency download

## Build
```bash
./gradlew clean build
