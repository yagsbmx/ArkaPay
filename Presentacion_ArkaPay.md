# 🏦 Arquitectura y Diseño del Microservicio de Pagos (ArkaPay)

**Proyecto:** ArkaPay  
**Tipo:** Microservicio Spring Boot  
**Arquitectura:** Hexagonal / Clean Architecture  
**Lenguaje:** Java  
**Build System:** Gradle  
**Autor:** Equipo ArkaPay  

---

## 🎯 Objetivo del Microservicio

El microservicio **ArkaPay** tiene como finalidad gestionar todo el flujo de **pagos** dentro del ecosistema Arka.  
Su responsabilidad abarca la creación, validación, procesamiento y comunicación de resultados de transacciones hacia otros servicios, como **órdenes** y **usuarios**.

---

## 🧩 Arquitectura General

El diseño sigue una **arquitectura limpia (Clean Architecture)** que separa el código en capas con responsabilidades bien definidas:

```
┌──────────────────────────────────────────────┐
│                  Presentation                │
│          (Controllers / Web Layer)           │
├──────────────────────────────────────────────┤
│              Application Layer               │
│       (Use Cases / Business Logic)           │
├──────────────────────────────────────────────┤
│                Domain Layer                  │
│     (Entities / Models / Ports / Rules)      │
├──────────────────────────────────────────────┤
│             Infrastructure Layer             │
│ (Adapters / Persistence / External Clients)  │
└──────────────────────────────────────────────┘
```

Cada capa depende solo de las capas internas, permitiendo un alto nivel de mantenibilidad y facilidad para realizar pruebas o sustituir dependencias.

---

## 🧱 Estructura de Carpetas Principal

```
arkapay/
 ├── src/main/java/com/example/pay/
 │   ├── application/
 │   │   └── service/
 │   │       └── PaymentService.java
 │   ├── domain/
 │   │   ├── model/
 │   │   │   └── Payment.java
 │   │   ├── enums/
 │   │   │   ├── PaymentMethod.java
 │   │   │   └── PaymentStatus.java
 │   │   └── ports/
 │   │       ├── in/
 │   │       │   └── PaymentUseCase.java
 │   │       └── out/
 │   │           ├── OrderPort.java
 │   │           ├── PaymentRepositoryPort.java
 │   │           └── UserPort.java
 │   ├── infraestructure/
 │   │   ├── adapter/
 │   │   │   ├── web/controller/PaymentController.java
 │   │   │   ├── persistence/
 │   │   │   │   ├── entity/PaymentEntity.java
 │   │   │   │   └── repository/PaymentJpaRepository.java
 │   │   │   └── out/
 │   │   │       ├── order/OrderFeignClient.java
 │   │   │       └── user/UserFeignClient.java
 │   │   ├── dto/
 │   │   │   ├── PaymentRequestDto.java
 │   │   │   └── PaymentResponseDto.java
 │   │   ├── config/
 │   │   │   ├── FeignAuthForwardConfig.java
 │   │   │   └── FeignGenericErrorDecoder.java
 │   │   └── mapper/PaymentMapper.java
 │   ├── config/
 │   │   └── OpenApiConfig.java
 │   └── PayApplication.java
 └── resources/
     └── application.yml
```

---

## 🧠 Capa Domain

📍 **Ubicación:** `com.example.pay.domain`

Contiene la **lógica central del negocio**:
- **Modelos (Entities):** `Payment` representa una transacción con atributos como monto, método y estado.  
- **Enums:** `PaymentMethod` y `PaymentStatus` definen valores válidos del sistema.  
- **Ports:**  
  - `in` → Interfaces de entrada (casos de uso).  
  - `out` → Interfaces hacia infraestructura (repositorio, servicios externos).

Esta capa no depende de frameworks ni de infraestructura.

---

## ⚙️ Capa Application

📍 **Ubicación:** `com.example.pay.application.service`

Implementa los **casos de uso del dominio**.  
La clase principal, `PaymentService`, orquesta la ejecución de operaciones como:

- Procesar un nuevo pago.  
- Validar datos de transacción.  
- Invocar puertos externos (`OrderPort`, `UserPort`, `PaymentRepositoryPort`).  

Esta capa contiene la lógica de **coordinación**, manteniendo la lógica de negocio pura dentro del dominio.

---

## 🌐 Capa Infrastructure

📍 **Ubicación:** `com.example.pay.infraestructure`

Incluye todos los elementos necesarios para **interactuar con el mundo exterior**:

- **Adapters (Web & Persistence):**
  - `PaymentController` → expone endpoints REST.
  - `PaymentPersistenceAdapter` → implementa `PaymentRepositoryPort`.
  - `PaymentJpaRepository` → acceso a base de datos.
- **Feign Clients:**
  - `OrderFeignClient` y `UserFeignClient` → comunicación con otros microservicios.
- **DTOs:** transporte de datos entre capas.
- **Mappers:** transforman entidades ↔ DTOs.
- **Config:** configuración de Feign y manejo de errores personalizados.

---

## ⚙️ Configuración Global

📍 **Ubicación:** `com.example.pay.config`

Incluye configuraciones de entorno y documentación:
- `OpenApiConfig` → Define la documentación automática con Swagger/OpenAPI.  
- `application.yml` → Variables del entorno, puertos, y parámetros de conexión.

---

## 🧩 Diagrama Simplificado de Interacción

```
[Client / API Gateway]
          |
          v
[PaymentController] --> [PaymentService]
          |                      |
          v                      v
  [PaymentRepositoryPort]   [OrderPort, UserPort]
          |                      |
          v                      v
  [JPA Repository]       [Feign Clients -> External Services]
```

---

## 🚀 Beneficios de la Arquitectura

- **Escalabilidad:** Separación por capas permite agregar funcionalidades sin romper la base.  
- **Testabilidad:** Cada capa es fácilmente aislable.  
- **Mantenibilidad:** Código limpio y organizado.  
- **Flexibilidad:** Capacidad de sustituir infraestructura (por ejemplo, cambiar de DB o cliente HTTP) sin modificar la lógica del dominio.  
- **Estándar corporativo:** Cumple buenas prácticas de microservicios Spring Boot y Clean Architecture.

---

## 🧾 Conclusión

El microservicio **ArkaPay** representa una implementación sólida y profesional de arquitectura limpia en un entorno empresarial.  
Su distribución modular, claridad estructural y desac acoplamiento entre capas lo convierten en una base confiable para integrar servicios de pago dentro de un ecosistema distribuido.

---

## 🧑‍💻 Contacto

**Equipo de Desarrollo ArkaPay**  
📧 soporte@arkapay.io  
💼 Arquitectura y Desarrollo de Software Distribuido
