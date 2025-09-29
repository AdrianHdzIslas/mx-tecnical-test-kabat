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
mvn spring-boot:run
```
