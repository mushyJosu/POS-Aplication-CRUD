# PointOfSaleAppV1

Aplicación Android de punto de venta desarrollada con **Kotlin y Jetpack Compose**, integrada con una API REST para autenticación y gestión de productos y categorías.

El proyecto busca aplicar principios de **arquitectura por capas, separación de responsabilidades y desarrollo orientado a componentes**, manteniendo una estructura preparada para crecer.

## Tecnologías

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **MVVM**
- **Repository Pattern**
- **Retrofit + OkHttp**
- **Kotlin Serialization**
- **Coroutines + StateFlow**
- **Navigation Compose**
- **JWT Authentication**
- **REST API**

## Arquitectura

El proyecto utiliza una arquitectura por capas basada en **MVVM + Repository Pattern**:

```text
Jetpack Compose
      │
      ▼
   ViewModel
      │
      ▼
  Repository
      │
      ▼
DTO / Mapper / Domain
      │
      ▼
Retrofit + OkHttp
      │
      ▼
   REST API
```

La UI se encarga de representar el estado, los `ViewModel` manejan la lógica de presentación y los repositorios abstraen el acceso a datos.

## Estructura del proyecto

```text
app/
└── src/main/java/
    └── ...
        ├── auth/
        │   ├── data/
        │   ├── domain/
        │   └── presentation/
        │
        ├── products/
        │   ├── data/
        │   ├── domain/
        │   └── presentation/
        │
        ├── core/
        │   ├── domain/
        │   ├── network/
        │   └── session/
        │
        ├── di/
        └── ui/
            └── theme/
```

La organización separa **datos, dominio y presentación**, evitando concentrar toda la lógica dentro de las pantallas.

## Autenticación

La aplicación utiliza autenticación mediante **JWT**.

```text
Login
  ↓
API REST
  ↓
JWT
  ↓
SessionManager
  ↓
Authorization: Bearer <token>
```

El token se conserva localmente para permitir solicitudes autenticadas posteriores.

## Funcionalidades principales

- Registro e inicio de sesión.
- Gestión de productos.
- Consulta de productos.
- Creación y edición de productos.
- Eliminación de productos.
- Gestión de categorías.
- Navegación entre pantallas.
- Manejo de estados de carga, éxito y error.

## Conceptos aplicados

Este proyecto permitió poner en práctica:

- Separación de responsabilidades.
- Arquitectura **MVVM**.
- Repository Pattern.
- DTO → Mapper → Domain Model.
- Consumo de APIs REST.
- Autenticación JWT.
- Manejo de estado con `StateFlow`.
- Inyección y composición de dependencias.
- Desarrollo de interfaces con Jetpack Compose.

## Propósito

Proyecto académico enfocado en demostrar la aplicación práctica de conceptos de **desarrollo Android y arquitectura de software**, priorizando código organizado, mantenible y escalable.

> **Estado:** Proyecto académico / portfolio.

### Próximas mejoras

- Implementar más módulos del sistema.
- Mejorar pruebas automatizadas.
- Incorporar manejo de sesión más completo.
- Mejorar validaciones y experiencia de usuario.
- Ampliar la cobertura de funcionalidades de la API.
