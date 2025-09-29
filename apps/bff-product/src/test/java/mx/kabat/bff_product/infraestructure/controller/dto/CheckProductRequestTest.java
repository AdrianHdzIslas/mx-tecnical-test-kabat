package mx.kabat.bff_product.infraestructure.controller.dto;
import mx.kabat.bff_product.infrastructure.controller.dto.CheckProductRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckProductRequestTest {

    @Test
    void testNoArgsConstructorAndSetter() {
        CheckProductRequest request = new CheckProductRequest();
        List<Long> ids = List.of(1L, 2L, 3L);
        request.setProducts(ids);

        assertEquals(ids, request.getProducts());
    }
}