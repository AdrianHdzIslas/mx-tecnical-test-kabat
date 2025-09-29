package mx.kabat.bff_product.infraestructure.controller.dto;

import mx.kabat.bff_product.infrastructure.controller.dto.SimpleProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.UpdateStockRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateStockRequestTest {

    @Test
    void testSetterAndGetter() {
        SimpleProductRequest product1 = new SimpleProductRequest();
        product1.setIdProduct(1L);
        product1.setAmount(3);

        SimpleProductRequest product2 = new SimpleProductRequest();
        product2.setIdProduct(2L);
        product2.setAmount(5);

        List<SimpleProductRequest> products = List.of(product1, product2);

        UpdateStockRequest request = new UpdateStockRequest();
        request.setProducts(products);

        assertEquals(2, request.getProducts().size());
        assertEquals(1L, request.getProducts().get(0).getIdProduct());
        assertEquals(5, request.getProducts().get(1).getAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        SimpleProductRequest product = new SimpleProductRequest();
        product.setIdProduct(10L);
        product.setAmount(7);

        List<SimpleProductRequest> productList = List.of(product);

        UpdateStockRequest req1 = new UpdateStockRequest();
        req1.setProducts(productList);

        UpdateStockRequest req2 = new UpdateStockRequest();
        req2.setProducts(productList);

        assertEquals(req1, req2);
        assertEquals(req1.hashCode(), req2.hashCode());
    }

    @Test
    void testToString() {
        SimpleProductRequest product = new SimpleProductRequest();
        product.setIdProduct(99L);
        product.setAmount(9);

        UpdateStockRequest request = new UpdateStockRequest();
        request.setProducts(List.of(product));

        String result = request.toString();

        assertTrue(result.contains("99"));
        assertTrue(result.contains("9"));
        assertTrue(result.contains("products"));
    }
}
