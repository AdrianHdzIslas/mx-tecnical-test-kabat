package mx.kabat.bff_product.infraestructure.controller.dto;

import mx.kabat.bff_product.infrastructure.controller.dto.SimpleProductRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleProductRequestTest {

    @Test
    void testSettersAndGetters() {
        SimpleProductRequest request = new SimpleProductRequest();
        request.setIdProduct(123L);
        request.setAmount(10);

        assertEquals(123L, request.getIdProduct());
        assertEquals(10, request.getAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        SimpleProductRequest r1 = new SimpleProductRequest();
        r1.setIdProduct(1L);
        r1.setAmount(5);

        SimpleProductRequest r2 = new SimpleProductRequest();
        r2.setIdProduct(1L);
        r2.setAmount(5);

        SimpleProductRequest r3 = new SimpleProductRequest();
        r3.setIdProduct(2L);
        r3.setAmount(3);

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        SimpleProductRequest request = new SimpleProductRequest();
        request.setIdProduct(99L);
        request.setAmount(2);

        String result = request.toString();
        assertTrue(result.contains("99"));
        assertTrue(result.contains("2"));
    }
}
