# API de Ordenes - Documentación Básica

## 📌 Endpoints principales

### 1. Crear una orden

**URL:** `POST /api/v1/order`

**Descripción:** Crea una nueva orden.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8082/api/v1/order' \
--header 'Content-Type: application/json' \
--data-raw '{
  "status": "CONFIRMED",
  "customerFullName": "Alejandro Gómez",
  "customerEmail": "alejandro.gomez@example.com",
  "items": [
    {
      "idProduct": 1,
      "quantity": 2,
      "price": 45.99
    },
    {
      "idProduct": 2,
      "quantity": 2,
      "price": 120.00
    }
  ]
}'
```

### 2. Obtener una orden por ID

**URL:** `GET /api/v1/order/{id}`

**Descripción:** Obtener una nueva orden por su id.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8082/api/v1/order/1'
```

### 3. Obtener todas las ordenes

**URL:** `GET /api/v1/order`

**Descripción:** Obtener todas las ordenes.

**Ejemplo curl:**

```bash
curl --location 'http://127.0.0.1:8082/api/v1/order'
```