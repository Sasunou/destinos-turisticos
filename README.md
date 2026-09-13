# API de Destinos Turísticos

## Descripción

API REST desarrollada con Java y Spring Boot para gestionar información sobre destinos turísticos.

El proyecto permite realizar operaciones CRUD (crear, consultar, actualizar y eliminar) sobre destinos turísticos, utilizando persistencia de datos mediante JPA, Hibernate y una base de datos H2.

La API también incluye una consulta personalizada que permite buscar destinos por país.

Este proyecto corresponde a la actividad de la asignatura **Lenguajes de Programación 3**, en la cual se aplican conceptos de desarrollo de APIs REST y persistencia de datos.

## Contexto

El contexto seleccionado para el desarrollo de la API son los **viajes y destinos turísticos**.

Los destinos utilizados en las pruebas corresponden a lugares turísticos de diferentes países, algunos de ellos visitados durante viajes internacionales.

La información gestionada por la API corresponde a:

- Ciudad o destino.
- País.
- Descripción.
- Estado de visita.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Visual Studio Code
- Bruno
- GitHub

## Persistencia de datos

La aplicación utiliza **JPA e Hibernate** para gestionar la persistencia de la información.

La entidad principal del proyecto es `Destino`, la cual está representada mediante una entidad JPA utilizando las anotaciones correspondientes.

La información se almacena en una base de datos **H2** configurada para persistir los datos en archivos locales.

La configuración utilizada permite conservar los registros incluso después de detener y volver a ejecutar la aplicación.

## Estructura del proyecto

```text
destinos-turisticos
│
├── .mvn
│   └── wrapper
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── api_destinos_turisticos
│   │   │       ├── controlador
│   │   │       │   └── DestinoControlador.java
│   │   │       │
│   │   │       ├── dto
│   │   │       │   └── DestinoSolicitud.java
│   │   │       │
│   │   │       ├── modelo
│   │   │       │   └── Destino.java
│   │   │       │
│   │   │       ├── repositorio
│   │   │       │   └── DestinoRepositorio.java
│   │   │       │
│   │   │       └── DestinosTuristicosApplication.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Entidad Destino

La entidad `Destino` representa los destinos turísticos almacenados en la base de datos.

Sus atributos son:

| Atributo | Tipo | Descripción |
|---|---|---|
| id | Long | Identificador único del destino |
| ciudad | String | Ciudad o destino turístico |
| pais | String | País donde se encuentra el destino |
| descripcion | String | Descripción general del destino |
| visitado | boolean | Indica si el destino ha sido visitado |

La entidad utiliza las anotaciones JPA necesarias para permitir su persistencia.

El identificador se genera automáticamente mediante:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

## Repositorio

El proyecto utiliza un repositorio basado en `JpaRepository`:

```java
public interface DestinoRepositorio extends JpaRepository<Destino, Long> {

    List<Destino> findByPaisIgnoreCase(String pais);

}
```

Al extender `JpaRepository`, el repositorio dispone de las operaciones necesarias para realizar las operaciones CRUD sobre la entidad `Destino`.

Entre las operaciones utilizadas se encuentran:

- `findAll()`
- `findById()`
- `save()`
- `existsById()`
- `deleteById()`

Además, se implementó una consulta personalizada mediante:

```java
findByPaisIgnoreCase(String pais)
```

Esta consulta permite buscar destinos por país.

## Operaciones CRUD y endpoints

La API utiliza como ruta base:

```text
http://localhost:8080/api/destinos
```

### 1. Crear un destino

**Método HTTP:**

```text
POST
```

**Endpoint:**

```text
/api/destinos
```

**Ejemplo de solicitud:**

```json
{
    "ciudad": "Kioto",
    "pais": "Japón",
    "descripcion": "Ciudad conocida por sus templos, jardines y cultura tradicional.",
    "visitado": false
}
```

La información recibida mediante `@RequestBody` es representada utilizando el DTO `DestinoSolicitud`.

**Respuesta exitosa:**

```text
201 Created
```

**Ejemplo de respuesta:**

```json
{
    "id": 1,
    "ciudad": "Kioto",
    "pais": "Japón",
    "descripcion": "Ciudad conocida por sus templos, jardines y cultura tradicional.",
    "visitado": false
}
```

### 2. Consultar todos los destinos

**Método HTTP:**

```text
GET
```

**Endpoint:**

```text
/api/destinos
```

**Ejemplo:**

```text
http://localhost:8080/api/destinos
```

Este endpoint consulta todos los destinos almacenados en la base de datos.

**Respuesta exitosa:**

```text
200 OK
```

### 3. Consultar un destino por identificador

**Método HTTP:**

```text
GET
```

**Endpoint:**

```text
/api/destinos/{id}
```

**Ejemplo:**

```text
http://localhost:8080/api/destinos/1
```

El endpoint utiliza `@PathVariable` para recibir el identificador del destino.

**Respuesta cuando el destino existe:**

```text
200 OK
```

**Respuesta cuando el destino no existe:**

```text
404 Not Found
```

### 4. Actualizar un destino

**Método HTTP:**

```text
PUT
```

**Endpoint:**

```text
/api/destinos/{id}
```

**Ejemplo:**

```text
http://localhost:8080/api/destinos/1
```

**Ejemplo de solicitud:**

```json
{
    "ciudad": "Osaka",
    "pais": "Japón",
    "descripcion": "Ciudad japonesa reconocida por su gastronomía, vida nocturna y lugares de interés.",
    "visitado": true
}
```

El identificador se recibe mediante `@PathVariable` y los nuevos datos mediante `@RequestBody`.

**Respuesta cuando el destino se actualiza correctamente:**

```text
200 OK
```

Si el identificador no existe:

```text
404 Not Found
```

### 5. Eliminar un destino

**Método HTTP:**

```text
DELETE
```

**Endpoint:**

```text
/api/destinos/{id}
```

**Ejemplo:**

```text
http://localhost:8080/api/destinos/1
```

Si el destino existe, se elimina de la base de datos.

**Respuesta exitosa:**

```text
204 No Content
```

Si el identificador no existe:

```text
404 Not Found
```

## Consulta personalizada

Además de las operaciones CRUD, la API incluye una consulta personalizada para buscar destinos por país.

**Método HTTP:**

```text
GET
```

**Endpoint:**

```text
/api/destinos/buscar?pais={pais}
```

**Ejemplo:**

```text
http://localhost:8080/api/destinos/buscar?pais=Vietnam
```

La consulta utiliza el método:

```java
findByPaisIgnoreCase(String pais)
```

Esto permite realizar la búsqueda sin diferenciar entre mayúsculas y minúsculas.

**Respuesta exitosa:**

```text
200 OK
```

## DTO

El proyecto utiliza un `record` como DTO para representar la información recibida al crear o actualizar un destino.

El DTO utilizado es `DestinoSolicitud`:

```java
public record DestinoSolicitud(
        String ciudad,
        String pais,
        String descripcion,
        boolean visitado
) {
}
```

El DTO permite separar los datos recibidos desde las solicitudes HTTP de la entidad persistente `Destino`.

## Respuestas HTTP

La API utiliza `ResponseEntity` para devolver códigos HTTP coherentes con las operaciones realizadas.

Los principales códigos utilizados son:

| Código | Significado | Uso en la API |
|---|---|---|
| 200 OK | Solicitud procesada correctamente | Consultas y actualización |
| 201 Created | Recurso creado correctamente | Creación de un destino |
| 204 No Content | Operación realizada sin contenido de respuesta | Eliminación de un destino |
| 404 Not Found | Recurso no encontrado | ID inexistente |

## Configuración de H2

La base de datos H2 se configura mediante el archivo:

```text
src/main/resources/application.properties
```

La aplicación utiliza una base de datos H2 almacenada localmente mediante:

```text
jdbc:h2:file:./data/destinosdb
```

Esta configuración permite conservar los datos entre diferentes ejecuciones de la aplicación.

La consola de H2 también se encuentra habilitada en:

```text
http://localhost:8080/h2-console
```

## Ejecución del proyecto

### Requisitos

Para ejecutar el proyecto se necesita:

- Java instalado.
- Visual Studio Code u otro entorno de desarrollo.
- Una terminal.

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven de forma independiente.

### Ejecutar en Windows

Abrir una terminal dentro de la carpeta raíz del proyecto y ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```

Cuando Spring Boot se encuentre funcionando correctamente, aparecerá un mensaje similar a:

```text
Started DestinosTuristicosApplication
```

La API estará disponible en:

```text
http://localhost:8080
```

## Pruebas

Los endpoints `GET` pueden probarse directamente desde un navegador web.

Por ejemplo:

```text
http://localhost:8080/api/destinos
```

```text
http://localhost:8080/api/destinos/1
```

```text
http://localhost:8080/api/destinos/buscar?pais=Vietnam
```

Las operaciones `POST`, `PUT` y `DELETE` pueden probarse utilizando Bruno.

## Persistencia

Para comprobar la persistencia de los datos:

1. Ejecutar la aplicación.
2. Crear uno o varios destinos mediante `POST`.
3. Consultar los destinos mediante `GET`.
4. Detener la aplicación.
5. Volver a ejecutar Spring Boot.
6. Consultar nuevamente los destinos.

Los registros creados anteriormente deben permanecer almacenados en la base de datos H2.

## Autor

Proyecto desarrollado individualmente como actividad académica de la asignatura Lenguajes de Programación 3.