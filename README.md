# API de Destinos Turísticos

API REST básica desarrollada con Java y Spring Boot para consultar y registrar destinos turísticos.

Este proyecto fue desarrollado como actividad académica de la asignatura Lenguajes de Programación 3, con el propósito de aplicar conceptos relacionados con el desarrollo de APIs REST, métodos HTTP, parámetros, intercambio de información mediante JSON, DTO y respuestas HTTP.

## Descripción del proyecto

La API permite consultar diferentes destinos turísticos y registrar nuevos destinos mediante solicitudes HTTP.

Los datos iniciales corresponden a destinos turísticos visitados durante diferentes viajes, incluyendo lugares de Japón, Indonesia, Tailandia, Vietnam y Turquía.

La aplicación utiliza una lista en memoria para almacenar los destinos. Por esta razón, los destinos registrados mediante el método POST se mantienen mientras la aplicación está en ejecución, pero se pierden cuando la aplicación se reinicia.

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Web
- Maven
- Visual Studio Code
- Bruno
- GitHub

## Estructura del proyecto

```text
destinos-turisticos
│
├── .mvn
│   └── wrapper
│       └── maven-wrapper.properties
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

## Modelo de datos

Cada destino turístico contiene los siguientes atributos:

| Atributo | Tipo | Descripción |
|---|---|---|
| id | Long | Identificador único del destino |
| ciudad | String | Ciudad o destino turístico |
| pais | String | País donde se encuentra el destino |
| descripcion | String | Descripción general del destino |
| visitado | boolean | Indica si el destino ha sido visitado |

## Destinos iniciales

La aplicación inicia con los siguientes destinos:

| ID | Ciudad | País | Visitado |
|---|---|---|---|
| 1 | Osaka | Japón | Sí |
| 2 | Tokio | Japón | Sí |
| 3 | Bali | Indonesia | Sí |
| 4 | Bangkok | Tailandia | Sí |
| 5 | Hanoi | Vietnam | Sí |
| 6 | Sapa | Vietnam | Sí |
| 7 | Ninh Binh | Vietnam | Sí |
| 8 | Estambul | Turquía | Sí |

## Endpoints

La API utiliza la siguiente ruta base:

```text
http://localhost:8080/api/destinos
```

### 1. Obtener todos los destinos

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

Este endpoint permite obtener todos los destinos turísticos registrados.

**Respuesta exitosa:**

```text
200 OK
```

**Ejemplo de respuesta:**

```json
[
    {
        "id": 1,
        "ciudad": "Osaka",
        "pais": "Japón",
        "descripcion": "Ciudad conocida por su gastronomía, vida nocturna y ambiente urbano.",
        "visitado": true
    },
    {
        "id": 2,
        "ciudad": "Tokio",
        "pais": "Japón",
        "descripcion": "Gran ciudad japonesa caracterizada por su tecnología, cultura y diversidad.",
        "visitado": true
    }
]
```

### 2. Obtener un destino por ID

Este endpoint utiliza `@PathVariable`.

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

El valor `1` corresponde al identificador del destino.

**Respuesta cuando el destino existe:**

```text
200 OK
```

**Ejemplo:**

```json
{
    "id": 1,
    "ciudad": "Osaka",
    "pais": "Japón",
    "descripcion": "Ciudad conocida por su gastronomía, vida nocturna y ambiente urbano.",
    "visitado": true
}
```

Si el identificador no existe, la API responde:

```text
404 Not Found
```

### 3. Buscar destinos por país

Este endpoint utiliza `@RequestParam`.

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

El parámetro `pais` permite filtrar los destinos registrados según el país.

**Respuesta exitosa:**

```text
200 OK
```

**Ejemplo de respuesta:**

```json
[
    {
        "id": 5,
        "ciudad": "Hanoi",
        "pais": "Vietnam",
        "descripcion": "Capital de Vietnam con una combinación de historia, arquitectura y gastronomía.",
        "visitado": true
    },
    {
        "id": 6,
        "ciudad": "Sapa",
        "pais": "Vietnam",
        "descripcion": "Destino montañoso conocido por sus paisajes, arrozales y comunidades locales.",
        "visitado": true
    },
    {
        "id": 7,
        "ciudad": "Ninh Binh",
        "pais": "Vietnam",
        "descripcion": "Región conocida por sus paisajes de montañas, ríos y formaciones naturales.",
        "visitado": true
    }
]
```

### 4. Registrar un nuevo destino

Este endpoint utiliza el método `POST` y recibe información en formato JSON mediante `@RequestBody`.

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

La información recibida es representada mediante el DTO `DestinoSolicitud`.

**Respuesta exitosa:**

```text
201 Created
```

**Ejemplo de respuesta:**

```json
{
    "id": 9,
    "ciudad": "Kioto",
    "pais": "Japón",
    "descripcion": "Ciudad conocida por sus templos, jardines y cultura tradicional.",
    "visitado": false
}
```

## DTO

El proyecto utiliza un `record` de Java como DTO para representar los datos recibidos mediante el método `POST`.

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

El DTO permite separar los datos que recibe la API de la estructura completa utilizada para representar un destino.

## ResponseEntity

La API utiliza `ResponseEntity` para construir respuestas HTTP coherentes con cada operación.

Por ejemplo, cuando se consulta correctamente un destino:

```java
return ResponseEntity.ok(destino);
```

La respuesta corresponde al código:

```text
200 OK
```

Cuando se registra un nuevo destino:

```java
return ResponseEntity.status(HttpStatus.CREATED)
        .body(nuevoDestino);
```

La respuesta corresponde al código:

```text
201 Created
```

Cuando no se encuentra un destino:

```java
return ResponseEntity.notFound().build();
```

La respuesta corresponde al código:

```text
404 Not Found
```

## Códigos HTTP utilizados

| Código | Significado | Situación |
|---|---|---|
| 200 OK | Solicitud procesada correctamente | Consultas GET exitosas |
| 201 Created | Recurso creado correctamente | Registro de un nuevo destino |
| 404 Not Found | Recurso no encontrado | ID de destino inexistente |

## Ejecución del proyecto

### Requisitos

Para ejecutar el proyecto se necesita:

- Java instalado.
- Visual Studio Code u otro entorno de desarrollo.
- Acceso a una terminal.

No es necesario instalar Maven por separado, ya que el proyecto incluye Maven Wrapper.

### Ejecutar en Windows

Abrir una terminal dentro de la carpeta raíz del proyecto y ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```

Cuando la aplicación se encuentre funcionando correctamente, aparecerá un mensaje similar a:

```text
Started DestinosTuristicosApplication
```

La API estará disponible en:

```text
http://localhost:8080
```

## Pruebas de la API

Los endpoints GET pueden probarse directamente desde un navegador web.

Ejemplos:

```text
http://localhost:8080/api/destinos
```

```text
http://localhost:8080/api/destinos/1
```

```text
http://localhost:8080/api/destinos/buscar?pais=Vietnam
```

El endpoint POST puede probarse mediante Bruno utilizando la siguiente configuración:

**Método:**

```text
POST
```

**URL:**

```text
http://localhost:8080/api/destinos
```

**Body:**

```json
{
    "ciudad": "Kioto",
    "pais": "Japón",
    "descripcion": "Ciudad conocida por sus templos, jardines y cultura tradicional.",
    "visitado": false
}
```

## Consideraciones

La aplicación utiliza una lista en memoria para almacenar los destinos. Esto permite implementar y probar los conceptos básicos de una API REST sin utilizar una base de datos.

Debido a que la información se almacena únicamente en memoria, los destinos agregados mediante `POST` se eliminan cuando la aplicación se detiene o se reinicia.

## Autor

Proyecto desarrollado individualmente como actividad académica de la asignatura Lenguajes de Programación 3.