# Repository Guidelines

## Project Structure & Module Organization

This is a Java 17, Spring Boot 3.5.x Maven project. Production code lives in `src/main/java` and tests in `src/test/java`. The application uses Controller, Service, and Repository layers, with in-memory user storage.

## Build, Test, and Development Commands

- `mvn test` runs the full test suite (JDK 17 required).
- `mvn spring-boot:run` starts the application.

## Coding Style & Naming Conventions

Use descriptive Java names and organize classes by responsibility under `com.example.usermanagement`.

## Testing Guidelines

Tests use Spring Boot Starter Test and the `*Test.java` naming convention. Cover endpoint behavior and important edge cases.

## Commit & Pull Request Guidelines

Use concise, imperative commit subjects. Pull requests should explain purpose, implementation, validation, and screenshots for user-facing changes.

## Security & Configuration Tips

Do not commit credentials, tokens, or machine-specific configuration.
