# API de Productos - Documentación Básica

## 📌 Endpoints principales

### 1. Crear un producto

**URL:** `POST /api/v1/product`

**Descripción:** Crea un nuevo producto.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8081/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
    "name":"laptop",
    "price":19999,
    "stock":16,
    "active": true
}'
```

### 2. Obtener un producto por ID

**URL:** `GET /api/v1/product/{id}`

**Descripción:** Obtener un producto por su id.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8082/api/v1/product/1'
```

### 3. Obtener todos los productos

**URL:** `GET /api/v1/product`

**Descripción:** Obtener todos los productos.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8081/api/v1/product'
```

### 4. Revisar disponibilidad

**URL:** `POST /api/v1/product/check-availability`

**Descripción:** Obtener la disponibilidad de los productos.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8080/api/v1/product/check-availability' \
--header 'Content-Type: application/json' \
--data '{
    "products":[1,2]
}'
```

### 5. Actualizar la existencia de los productos

**URL:** `PATCH /api/v1/product/stock`

**Descripción:** Actualiza la existencia de los productos.

**Ejemplo curl:**

```bash
curl --location --request PATCH 'http://127.0.0.1:8081/api/v1/product/stock' \
--header 'Content-Type: application/json' \
--data '{
    "products":[
        {"idProduct":1,"amount": -1},
        {"idProduct":2,"amount": -1}
    ]
}'
```