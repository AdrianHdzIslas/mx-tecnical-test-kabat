package mx.kabat.bff_product.infraestructure.controller.mapper;

import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import mx.kabat.bff_product.infrastructure.controller.dto.ProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.ProductResponse;
import mx.kabat.bff_product.infrastructure.controller.dto.SimpleProductRequest;
import mx.kabat.bff_product.infrastructure.controller.mapper.ProductMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void testToDomain_fromProductRequest() {
        ProductRequest request = new ProductRequest();
        request.setIdProduct(1L);
        request.setName("Monitor");
        request.setPrice(new BigDecimal("2499.99"));
        request.setStock(8);
        request.setActive(true);

        ProductModel model = ProductMapper.toDomain(request);

        assertNotNull(model);
        assertEquals(1L, model.idProduct());
        assertEquals("Monitor", model.name());
        assertEquals(new BigDecimal("2499.99"), model.price());
        assertEquals(8, model.stock());
        assertTrue(model.active());
    }

    @Test
    void testToResponse_fromProductModel() {
        ProductModel model = new ProductModel(
                2L,
                "Telefono",
                new BigDecimal("8999.00"),
                15,
                true
        );

        ProductResponse response = ProductMapper.toResponse(model);

        assertNotNull(response);
        assertEquals(2L, response.getIdProduct());
        assertEquals("Telefono", response.getName());
        assertEquals(new BigDecimal("8999.00"), response.getPrice());
        assertEquals(15, response.getStock());
        assertTrue(response.getActive());
    }

    @Test
    void testToDomain_fromSimpleProductRequest() {
        SimpleProductRequest request = new SimpleProductRequest();
        request.setIdProduct(3L);
        request.setAmount(4);

        SimpleProductInfo info = ProductMapper.toDomain(request);

        assertNotNull(info);
        assertEquals(3L, info.idProduct());
        assertEquals(4, info.amount());
    }
}

