# API de Destinos Turísticos

API REST desarrollada con Java y Spring Boot para gestionar destinos turísticos. El proyecto permite realizar operaciones CRUD sobre destinos, relacionarlos con países, consultar destinos por país y consumir un servicio externo de tasas de cambio.

## Tecnologías utilizadas

* Java 26
* Spring Boot 4.1.1
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* RestClient
* Spring Boot Actuator
* Micrometer
* Prometheus
* Bruno para pruebas de API

## Funcionalidades

### Gestión de destinos

La API permite:

* Consultar todos los destinos.
* Consultar un destino por ID.
* Crear destinos.
* Actualizar destinos.
* Eliminar destinos.
* Buscar destinos por nombre de país.

### Relación entre destinos y países

Cada destino está asociado con un país mediante una relación `ManyToOne`.

La estructura principal es:

```text
Pais
  │
  └── 1:N ── Destino
```

La tabla `destino` utiliza la columna `pais_id` como clave foránea hacia la tabla `pais`.

### Persistencia

La información se almacena en una base de datos MySQL llamada:

```text
destinos_turisticos
```

Hibernate/JPA se encarga de la persistencia de las entidades.

## Endpoints principales

### Destinos

| Método | Endpoint                           | Descripción                |
| ------ | ---------------------------------- | -------------------------- |
| GET    | `/api/destinos`                    | Obtener todos los destinos |
| GET    | `/api/destinos/{id}`               | Obtener un destino por ID  |
| GET    | `/api/destinos/buscar?pais={pais}` | Buscar destinos por país   |
| POST   | `/api/destinos`                    | Crear un destino           |
| PUT    | `/api/destinos/{id}`               | Actualizar un destino      |
| DELETE | `/api/destinos/{id}`               | Eliminar un destino        |

### Ejemplo para crear un destino

```json
{
  "ciudad": "Medellín",
  "paisId": 2,
  "descripcion": "Ciudad de la eterna primavera",
  "visitado": false
}
```

## API externa de tasas de cambio

El proyecto consume la API externa de **Frankfurter** utilizando `RestClient`.

Endpoint interno:

```text
GET /api/tasas?base=USD&quote=COP
```

Ejemplo:

```text
GET http://localhost:8080/api/tasas?base=USD&quote=COP
```

La respuesta contiene:

* Fecha de la tasa.
* Moneda base.
* Moneda de destino.
* Tasa de cambio.

También se implementó manejo de errores para controlar problemas al consultar el servicio externo.

## Observabilidad

El proyecto utiliza Spring Boot Actuator para monitorear el estado de la aplicación.

### Health

```text
GET /actuator/health
```

Permite verificar el estado de la aplicación y de la conexión con MySQL.

### Métricas

```text
GET /actuator/metrics
```

Permite consultar las métricas disponibles de la aplicación.

### Métrica propia

Se creó la métrica:

```text
tasas.consultadas
```

Esta registra la cantidad de consultas realizadas al servicio de tasas de cambio.

Puede consultarse mediante:

```text
GET /actuator/metrics/tasas.consultadas
```

### Prometheus

Las métricas están disponibles para Prometheus mediante:

```text
GET /actuator/prometheus
```

### Logs

La aplicación utiliza el sistema de logging de Spring Boot para registrar eventos importantes. Por ejemplo, las consultas realizadas al servicio de tasas de cambio.

## Estructura principal del proyecto

```text
src/
└── main/
    ├── java/
    │   └── api_destinos_turisticos/
    │       ├── config/
    │       ├── controlador/
    │       ├── dto/
    │       ├── modelo/
    │       ├── repositorio/
    │       └── servicio/
    │
    └── resources/
        └── application.properties
```

## Ejecución del proyecto

Para ejecutar la aplicación utilizando Maven Wrapper:

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

La aplicación se ejecuta por defecto en:

```text
http://localhost:8080
```

## Base de datos

El proyecto utiliza MySQL.

Base de datos:

```text
destinos_turisticos
```

Tablas principales:

```text
pais
destino
```

La configuración de conexión se encuentra en `application.properties`.

Por seguridad, las credenciales de la base de datos no deben publicarse en el repositorio.

## Pruebas

Los endpoints fueron probados utilizando Bruno.

Se verificaron:

* Operaciones CRUD de destinos.
* Consulta de destinos por país.
* Relación entre `Destino` y `Pais`.
* Persistencia en MySQL.
* Consumo de la API externa.
* Manejo de errores de la API externa.
* Estado de la aplicación mediante Actuator.
* Métricas de Spring Boot.
* Métrica personalizada.
* Exposición de métricas para Prometheus.

## Autor

Proyecto académico desarrollado para la asignatura **Lenguajes de Programación 3**.
