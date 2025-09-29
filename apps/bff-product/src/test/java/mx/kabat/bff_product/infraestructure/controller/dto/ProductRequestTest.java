package mx.kabat.bff_product.infraestructure.controller.dto;

import mx.kabat.bff_product.infrastructure.controller.dto.ProductRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestTest {

    @Test
    void testAllArgsConstructor() {
        ProductRequest request = new ProductRequest(
                1L,
                "Lap top",
                new BigDecimal("99.99"),
                10,
                true
        );

        assertEquals(1L, request.getIdProduct());
        assertEquals("Lap top", request.getName());
        assertEquals(new BigDecimal("99.99"), request.getPrice());
        assertEquals(10, request.getStock());
        assertTrue(request.getActive());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        ProductRequest request = new ProductRequest();

        request.setIdProduct(2L);
        request.setName("HDD");
        request.setPrice(new BigDecimal("49.99"));
        request.setStock(5);
        request.setActive(false);

        assertEquals(2L, request.getIdProduct());
        assertEquals("HDD", request.getName());
        assertEquals(new BigDecimal("49.99"), request.getPrice());
        assertEquals(5, request.getStock());
        assertFalse(request.getActive());
    }

    @Test
    void testEqualsAndHashCode() {
        ProductRequest r1 = new ProductRequest(1L, "HDD 2", new BigDecimal("10.0"), 5, true);
        ProductRequest r2 = new ProductRequest(1L, "HDD 2", new BigDecimal("10.0"), 5, true);
        ProductRequest r3 = new ProductRequest(2L, "Monitor", new BigDecimal("20.0"), 10, false);

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        ProductRequest request = new ProductRequest(1L, "Ventilador", new BigDecimal("15.5"), 3, true);
        String result = request.toString();

        assertTrue(result.contains("Ventilador"));
        assertTrue(result.contains("15.5"));
        assertTrue(result.contains("3"));
    }
}
