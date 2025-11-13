# 💳 Arka Pay Service

Microservicio **Pay** del ecosistema **Arka**, encargado de procesar pagos de órdenes, validar transacciones y comunicar el estado de pago a otros servicios.  
Desarrollado con **Spring Boot 3.5**, **Java 21**, **Gradle**, **Eureka**, **PostgreSQL** y autenticación **JWT**.

## 🚀 Funcionalidades principales
- Procesamiento y validación de pagos.
- Integración con el microservicio **Order** para confirmar o rechazar órdenes.
- Comunicación con **User** para notificaciones.
- Manejo de respuestas de éxito, error y reintentos.
- Documentación con **Swagger / OpenAPI 3.0**.
- Integración con **Eureka Discovery Server**.
- Configuración para ejecución local y Docker.
- Preparado para **CI/CD** con GitHub Actions.

## 🧱 Tecnologías
| Componente | Descripción |
|-------------|-------------|
| Java | 21 |
| Spring Boot | 3.5.x |
| Spring Cloud | 2025.x |
| Spring Security | Autenticación JWT |
| PostgreSQL | Base de datos |
| Feign | Comunicación interservicios |
| Eureka Client | Registro de microservicios |
| SpringDoc OpenAPI | Documentación Swagger |
| Docker / Compose | Contenedorización |

## ⚙️ Configuración
### `application.yml`
```yaml
server:
  port: 8085

spring:
  application:
    name: pay-service

  datasource:
    url: jdbc:postgresql://localhost:5432/paydb
    username: postgres
    password: 0921

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html

security:
  jwt:
    secret: ${SECURITY_JWT_SECRET:short_secret_key_32_chars_len!!!}
```

## 🔐 Seguridad
Autenticación por token **JWT** usando encabezado:
```
Authorization: Bearer <token>
```

Rutas públicas:
- `/swagger-ui.html`, `/swagger-ui/**`
- `/v3/api-docs/**`
- `/actuator/**`

## 📘 Endpoints principales
| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| POST | `/api/payments/create` | Crea un nuevo pago |
| GET | `/api/payments/{id}` | Consulta el estado de un pago |
| GET | `/api/payments/order/{orderId}` | Busca pagos asociados a una orden |
| PUT | `/api/payments/{id}/confirm` | Confirma un pago |
| PUT | `/api/payments/{id}/reject` | Rechaza un pago |

## 🧾 Swagger / OpenAPI
**Swagger UI:** [http://localhost:8088/swagger-ui.html](http://localhost:8088/swagger-ui.html)  
Archivo de configuración:  
`src/main/java/com/example/arkapay/config/OpenApiConfig.java`

## 🐳 Docker

### Dockerfile
```dockerfile
FROM gradle:8.10.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml
```yaml
services:
  postgres:
    image: postgres:15
    container_name: paydb
    environment:
      POSTGRES_DB: paydb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 0921
    ports:
      - "5432:5432"

  pay-service:
    build: .
    container_name: pay-service
    ports:
      - "8085:8085"
    environment:
      SPRING_PROFILES_ACTIVE: docker
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 0921
      EUREKA_SERVER_URL: http://eureka:8761/eureka/
      SECURITY_JWT_SECRET: short_secret_key_32_chars_len!!!
    depends_on:
      - postgres
```

## 🛠️ CI/CD (GitHub Actions)

Archivo: `.github/workflows/ci.yml`
```yaml
name: CI - Pay Service

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Build with Gradle
        run: ./gradlew clean build -x test

      - name: Test
        run: ./gradlew test

      - name: Build Docker image
        run: docker build -t yagsbmx/arkapay:latest .

      - name: Push to DockerHub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKERHUB_USERNAME }}
          password: ${{ secrets.DOCKERHUB_TOKEN }}
      - run: docker push yagsbmx/arkapay:latest
```

## 📄 Licencia
Proyecto bajo licencia **Apache 2.0**  
© 2025 Ecosistema Arka – Todos los derechos reservados.
