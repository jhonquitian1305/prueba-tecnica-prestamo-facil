# 📋 Préstamos Fácil API

API REST para la gestión y evaluación automática de solicitudes de préstamos desarrollada con **Spring Boot**, **PostgreSQL** y **Docker Compose**, siguiendo una Arquitectura Hexagonal.

---

# 🚀 Tecnologías

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Spring Data JPA
- OpenAPI 3 (Swagger)

---

# 📦 Requisitos

Antes de ejecutar el proyecto debe tener instalado:

- Docker
- Docker Compose
- Git

# 🚀 Inicio rápido

```bash
git clone https://github.com/jhonquitian1305/prueba-tecnica-prestamo-facil

cd prueba-tecnica-prestamo-facil

cp .env.example .env

docker compose up --build
```

Una vez iniciado el proyecto podrá acceder a:

- API(usando postman o similares): http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui/index.html

---

# ⚙️ Variables de entorno

El proyecto utiliza un archivo `.env` para configurar las variables de entorno necesarias para Docker y la aplicación.

Cree un archivo llamado:

```text
.env
```

con una estructura similar a la siguiente:

```env
PORT_APP=8080
HOST_DB=localhost
PORT_DB=5433
NAME_DB=prestamo_facil
USER_DB=postgres
PASSWORD_DB=MySecretPassword@/
MODE_SQL=always
PGADMIN_PORT=8090
PGADMIN_USER=admin@admin.com
PGADMIN_PASSWORD=admin

```

> **Nota:** Puede tomar como referencia el archivo `.env.example` incluido en el proyecto.

---

# ▶️ Ejecución

Desde la raíz del proyecto ejecute:

```bash
docker compose up --build
```

O en segundo plano:

```bash
docker compose up -d --build
```

---

# 🗄️ Inicialización de la base de datos

Durante la primera ejecución, PostgreSQL inicializa automáticamente la base de datos ejecutando los scripts ubicados en la carpeta:

```text
database/
```

Los scripts se ejecutan en el siguiente orden:

```text
database/
├── 01-create-tables.sql
├── 02-data.sql
└── 03-procedures.sql
```

Estos scripts realizan automáticamente:

- Creación de tablas.
- Inserción de datos maestros.
- Creación del procedimiento almacenado para la evaluación automática de préstamos.

> **Importante:** Estos scripts solo se ejecutan cuando la base de datos se crea por primera vez.

Si desea reinicializar completamente la base de datos:

```bash
docker compose down -v
docker compose up --build
```

---

# 🌐 Acceso a la API

Una vez iniciada la aplicación estará disponible en:

```
http://localhost:8080/api/v1
```

---

# 📚 Documentación de la API

La documentación interactiva de la API está disponible mediante Swagger UI.

Después de iniciar la aplicación acceda a:

```
http://localhost:8080/swagger-ui/index.html
```

La especificación OpenAPI también está disponible en:

```
http://localhost:8080/api-docs
```

Desde Swagger podrá:

- Consultar todos los endpoints.
- Ver los modelos de petición y respuesta.
- Ejecutar pruebas directamente desde el navegador.
- Revisar los códigos de respuesta del servicio.

---

# 🛢️ Base de datos

Las credenciales de conexión se encuentran definidas en el archivo `.env`.

Puede conectarse mediante herramientas como:

- pgAdmin
- DBeaver

## Ingreso cliente para base de datos (pgAdmin)

- Ir al link
  ```
  http://localhost:8090/login
  ```
- Iniciar sesión con datos en `.env`

- Registrar server
  ![register-server](/docs/images/register-server.png)

- Datos server
  ![server-data](/docs/images/server-data.png)

- Ingreso a la base de datos, ve toda la información
  ![view-data](/docs/images/view-data.png)
---

# 📁 Estructura del proyecto

```
.
├── database
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── application
│   │   │   ├── domain
│   │   │   ├── infrastructure
│   │   └── resources
│   └── test
├── docker-compose.yml
├── Dockerfile
├── .env.example
└── README.md
```

---

# 🏗️ Arquitectura

El proyecto implementa una Arquitectura Hexagonal (Ports & Adapters), separando la lógica de negocio de la infraestructura y facilitando el mantenimiento, las pruebas y la extensibilidad.
Se basó en la siguiente imagen en arquitectura del proyecto, además de la documentación que se encuentra allí presente

  ![ports-adapters](/docs/images/ports-adapters.png)

imagen tomada de
```
https://medium.com/@edusalguero/arquitectura-hexagonal-59834bb44b7f
```

---

# ✅ Funcionalidades

- Registro de solicitudes de préstamo.
- Consulta paginada de solicitudes.
- Evaluación automática mediante procedimiento almacenado.
- Aprobación y rechazo de préstamos.
- Generación automática del plan de pagos.
- Consulta del valor total de préstamos aprobados.

---

# 🛑 Detener la aplicación

```bash
docker compose down
```

Eliminar también los volúmenes:

```bash
docker compose down -v
```

---

# 🔄 Funcionamiento

## Usuarios

- Creación de un usuario
    ![register-user](/docs/images/register-user.png)

- Creación de un usuario cuando el email existe
  ![register-user-email-exists](/docs/images/register-user-email-exists.png)

## Préstamos

- Creación de un préstamo
  ![create-loan](/docs/images/create-loan.png)

- Creación de un préstamo cuando el tipo de préstamo no existe
  ![create-loan-type-loan-does-not-exist](/docs/images/create-loan-type-loan-does-not-exist.png)

- Creación de un préstamo cuando el usuario no existe
  ![create-loan-user-does-not-exist](/docs/images/create-loan-user-does-not-exist.png)

- Obteniendo todos los préstamos paginados
  ![get-loans](/docs/images/get-loans.png)

- Actualizando estado del préstamo
  ![update-state-loan](/docs/images/update-state-loan.png)

- Obteniendo el valor total de los préstamos aprobados
  ![sum-total-approved](/docs/images/sum-total-approved.png)

# 👨‍💻 Autor

Desarrollado como solución para una prueba técnica utilizando Spring Boot, PostgreSQL y Arquitectura Hexagonal.