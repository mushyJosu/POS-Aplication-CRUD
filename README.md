Point of Sale (POS) App - Android & FastAPI

Una aplicación móvil nativa para Android diseñada para modernizar y agilizar las operaciones de ventas, cobro y gestión de inventario en negocios. Respaldada por un backend en FastAPI, esta plataforma permite procesar transacciones de forma fluida, gestionar catálogos de productos y mantener una sincronización de datos eficiente, segura y escalable.

Arquitectura del Proyecto

El proyecto sigue los principios de Clean Architecture (Arquitectura Limpia) y el patrón de diseño MVVM (Model-View-ViewModel). Esta separación en capas asegura que la lógica de negocio sea independiente de la interfaz de usuario y de las fuentes de datos (API/Base de datos).

Diagrama de Flujo y Capas

graph TD
    subgraph Capa de Presentación / UI
        UI[Jetpack Compose Screens]
        VM[ViewModels & StateFlow]
    end
    
    subgraph Capa de Dominio / Lógica de Negocio
        RepoInterface[Interfaces de Repositorio\nProductsRepository]
        Models[Modelos de Dominio\nProduct]
    end
    
    subgraph Capa de Datos / Red
        RepoImpl[Implementación de Repositorio\nProductRepositoryImpl]
        Mappers[Mappers\nProductoDto a Product]
        API[Servicio API \nRetrofit / Interceptors]
        Local[Almacenamiento Local\nDataStore]
    end
    
    subgraph Backend API
        FastAPI[API REST \nFastAPI - Python]
        DB[(PostgreSQL)]
    end

    UI -- "Eventos del Usuario" --> VM
    VM -- "Actualiza UI (UiState)" --> UI
    VM -- "Solicita Datos" --> RepoInterface
    RepoImpl -. "Implementa" .-> RepoInterface
    RepoImpl -- "Mapea DTOs a Dominio" --> Mappers
    RepoImpl -- "Peticiones HTTP" --> API
    RepoImpl -- "Manejo de Sesión" --> Local
    API <== "JSON / HTTPS" ==> FastAPI
    FastAPI <== "SQL" ==> DB


Principios de Desarrollo Implementados

Clean Architecture:
El código está dividido en tres capas principales (presentation, domain, data). Esto permite que un cambio en la API (Capa de Datos) no rompa la Interfaz Gráfica (Capa de Presentación), siempre y cuando el Dominio se mantenga intacto.

MVVM (Model-View-ViewModel):
La vista (ProductsPage.kt) solo se encarga de dibujar píxeles en la pantalla. Toda la lógica de retención de datos, reglas de validación y comunicación con los repositorios vive en el ProductsViewModel.

Inyección de Dependencias (DI):
Uso de un contenedor de dependencias (ej. AppContainer) para proveer instancias de repositorios, clientes HTTP y ViewModels de manera desacoplada, facilitando el testing y la escalabilidad.

Principios SOLID aplicados:

Single Responsibility Principle (SRP): Clases pequeñas con un único propósito (ej. ProductMapper solo transforma datos de red a dominio).

Dependency Inversion Principle (DIP): El ViewModel (ProductsViewModel) no conoce cómo se obtienen los datos de internet; solo habla con la interfaz del repositorio (ProductsRepository), no con la implementación (ProductRepositoryImpl).

Flujo de Datos Unidireccional (UDF) y Reactividad:
El estado fluye hacia abajo (desde el ViewModel a la UI vía StateFlow) y los eventos fluyen hacia arriba (desde la UI al ViewModel). La pantalla es un reflejo en tiempo real del UiState.

Tecnologías Core

Frontend (Android / Kotlin)

Jetpack Compose: Creación de UI 100% declarativa.

Coroutines & Flow: Manejo asíncrono, multihilo y programación reactiva.

Retrofit 2 & OkHttp: Cliente HTTP tipado con interceptores para inyección de tokens de autenticación JWT.

DataStore: Persistencia de preferencias ligeras de usuario (Tokens de sesión).

Backend (Python)

FastAPI: Framework web asíncrono y de alto rendimiento para la creación de APIs REST.

SQLAlchemy & Pydantic: ORM para conexión a base de datos y validación estricta de esquemas de datos.

PostgreSQL: Base de datos relacional para el manejo íntegro del inventario y transacciones.

Características Principales

Módulo de Autenticación: Login seguro, manejo del ciclo de vida del token de sesión en la app e interceptores en peticiones protegidas.

Gestión de Catálogo e Inventario: Consumo asíncrono de los artículos de la tienda en tiempo real, cacheo de estados en la app y manejo de errores limpio (AppResult).

Motor de Punto de Venta (POS): Sistema ágil para armar carritos de compras, calcular subtotales, impuestos y preparar el payload transaccional para el cobro.

Procesamiento de Checkout (En desarrollo): Enlace entre el ticket local y el registro histórico en la base de datos centralizada del negocio a través de la API.

Configuración e Instalación Local

Clona este repositorio:

git clone https://github.com/TU_USUARIO/TU_REPOSITORIO.git


Abre el proyecto en Android Studio.

Configura la URL base de tu API en el archivo de configuración correspondiente (ej. en KtAplication o tu módulo de inyección de red).

Sincroniza Gradle (Sync Project with Gradle Files).

Compila y ejecuta el proyecto en un emulador o dispositivo físico físico Android.

Nota: Para el backend, revisa el repositorio https://github.com/mushyJosu/pos-api.git para levantar FastAPI y PostgreSQL localmente.
