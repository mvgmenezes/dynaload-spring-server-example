# Dynaload Spring Server Example

This is a full Spring Boot application demonstrating how to integrate with the Dynaload framework for dynamic remote class exportation and invocation. It combines a standard Spring Boot REST API with Dynaload annotations and auto-bootstrap logic to expose services and entities to Dynaload clients.

---

## Overview

This project uses:

- `@DynaloadExport` to export model classes (like `User`)
- `@DynaloadService` and `@DynaloadCallable` to expose Spring beans and their methods for remote invocation
- Embedded Dynaload socket server (via `dynaload-spring-starter`) running on port `9999` (configurable)
- Basic HTTP security via `SecurityFilterChain`

---

## Maven Coordinates

Ensure you include the following dependency to use Dynaload auto-boot:

```xml
<dependency>
    <groupId>io.dynaload.spring</groupId>
    <artifactId>dynaload-spring-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

---

## REST Endpoints

These endpoints are part of the local Spring Boot API (not related to Dynaload):

### `UserController`
| Method | Endpoint        | Description              |
|--------|------------------|--------------------------|
| GET    | `/users`         | List all users           |
| GET    | `/users/{id}`    | Get user by ID           |
| POST   | `/users`         | Create new user          |
| DELETE | `/users/{id}`    | Delete user by ID        |

---

## Dynaload Integration

### Entity Example

```java
@Entity
@Table(name = "users")
@DynaloadExport("v1/user")
public class User { ... }
```

This makes the `User` class available to Dynaload clients by requesting `v1/user`.

### Callable Service Example

```java
@DynaloadService
@Service
public class UserService {

    @DynaloadCallable
    public List<User> getAllUsers() { ... }

    @DynaloadCallable
    public Optional<User> getUserById(Long id) { ... }

    ...
}
```

Clients will be able to invoke methods like:
- `userService::getAllUsers`
- `userService::createUser`

---

## Security Configuration

The app is secured via basic HTTP auth:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/users/**").authenticated()
            .anyRequest().permitAll()
        )
        .httpBasic();
}
```

---

## Application Properties

```yaml
spring:
  application:
    name: dynaload-spring-server-example

  jpa:
    hibernate:
      ddl-auto: update

dynaload:
  enabled: true
  port: 9999
  basePackage: io.dynaload.example.spring.server
```

The `dynaload` block configures the socket server and what packages it should scan for annotated classes.

---

## ▶How to Run

```bash
./mvnw spring-boot:run
```

Or run the main class:

```java
@SpringBootApplication
public class DynaloadSpringServerExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynaloadSpringServerExampleApplication.class, args);
    }
}
```

> `DynaloadAutoBootstrap.init()` will automatically scan the classpath and start the embedded socket server.

---

## Structure

```
src/
└── main/
    └── java/
        └── io.dynaload.example.spring.server/
            ├── model/        // User entity
            ├── controller/   // UserController (REST)
            ├── repository/   // JPA repository
            ├── service/      // Dynaload services
            └── config/       // Spring Security
```

---

## Requirements

- Java 21
- Maven
- Dynaload JARs published to local `.m2` if not released

---

## Notes

- Dynaload server is launched automatically on application startup.
- Any Spring bean annotated with `@DynaloadService` and `@DynaloadCallable` is eligible for remote invocation.
- All model classes exported via `@DynaloadExport` become dynamically fetchable by Dynaload clients.

