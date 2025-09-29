# 📦 Tecnical Test

Este proyecto forma parte de una arquitectura distribuida de microservicios que son responsables de la gestión de **órdenes y productos**.

> 💡 Este repositorio contiene múltiples aplicaciones (`bff-order` y `bff-product`) ubicadas en `apps/`.

---

Antes de ejecutar este servicio, asegúrate de tener instaladas las siguientes herramientas:

### 🔧 Herramientas obligatorias

| 🛠️ Herramienta | 💡 Versión mínima | 📎 Enlace de instalación                  |
|----------------|-------------------|------------------------------------------|
| Java           | 17                | https://adoptium.net/                    |
| Maven          | 3.9+              | https://maven.apache.org/download.cgi   |

### 🧪 Para desarrollo local

- 🗃️ **H2 Database** (embebida, ya configurada)
- 🔍 **Postman** o **cURL** para probar los endpoints

---

## 🧪 Cómo ejecutar en local

### 📥 1. Clonar el repositorio

```bash
git clone https://github.com/AdrianHdzIslas/mx-tecnical-test-kabat.git
cd mx-tecnical-test-kabat
```

### 📂 2. Seleccionar el proyecto

```bash
cd apps/bff-product   # Microservicio de productos
# o
cd apps/bff-order     # Microservicio de órdenes
```
### ▶️ 3. Ejecutar el proyecto

```bash
mvn clean install
mvn spring-boot:run #Los puertos `8082` y `8081`  deben estar disponibles.
```
### 📘 4. Ejemplos de uso

La documentacion de uso de las apis se encuentran en los siguientes directorios:

+ [bbf-order](./apps/bff-order/HELP.md)

+ [bbf-product](./apps/bff-product/HELP.md)


### ▶️ 5. Ejecutar prubas unitarias

```bash
mvn test #Segun el Microservicio seleccionado
```
## 📘 Documentación de la API (Swagger UI)

Este proyecto expone su documentación de endpoints REST utilizando **Swagger UI**, gracias a la integración con [SpringDoc OpenAPI](https://springdoc.org/).

### ✅ Acceso a la documentación

Una vez que la aplicación esté en ejecución, puedes acceder a Swagger UI desde:

`bff-order` http://localhost:8082/swagger-ui/index.html

`bff-order` http://localhost:8081/swagger-ui/index.html

