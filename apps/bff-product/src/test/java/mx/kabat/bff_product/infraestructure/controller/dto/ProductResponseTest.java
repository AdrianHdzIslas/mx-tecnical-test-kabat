package mx.kabat.bff_product.infraestructure.controller.dto;

import mx.kabat.bff_product.infrastructure.controller.dto.ProductResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseTest {

    @Test
    void testAllArgsConstructor() {
        ProductResponse response = new ProductResponse(
                101L,
                "Tele",
                new BigDecimal("8999.99"),
                12,
                true
        );

        assertEquals(101L, response.getIdProduct());
        assertEquals("Tele", response.getName());
        assertEquals(new BigDecimal("8999.99"), response.getPrice());
        assertEquals(12, response.getStock());
        assertTrue(response.getActive());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        ProductResponse response = new ProductResponse();

        response.setIdProduct(102L);
        response.setName("Laptop");
        response.setPrice(new BigDecimal("18499.50"));
        response.setStock(7);
        response.setActive(false);

        assertEquals(102L, response.getIdProduct());
        assertEquals("Laptop", response.getName());
        assertEquals(new BigDecimal("18499.50"), response.getPrice());
        assertEquals(7, response.getStock());
        assertFalse(response.getActive());
    }

    @Test
    void testEqualsAndHashCode() {
        ProductResponse r1 = new ProductResponse(201L, "Auriculares", new BigDecimal("1299.00"), 30, true);
        ProductResponse r2 = new ProductResponse(201L, "Auriculares", new BigDecimal("1299.00"), 30, true);
        ProductResponse r3 = new ProductResponse(202L, "Camara", new BigDecimal("4500.00"), 15, true);

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        ProductResponse response = new ProductResponse(301L, "Tablet", new BigDecimal("2599.99"), 20, true);
        String result = response.toString();

        assertTrue(result.contains("Tablet"));
        assertTrue(result.contains("2599.99"));
        assertTrue(result.contains("20"));
    }
}

