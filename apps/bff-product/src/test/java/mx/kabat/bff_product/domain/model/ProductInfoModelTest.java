package mx.kabat.bff_product.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductInfoModelTest {

    @Test
    void testIsAvailable_WhenActiveAndStockPositive() {
        ProductInfoModel product = new ProductInfoModel(1L
                , new BigDecimal("100.99")
                , 5,
                true);
        assertTrue(product.isAvailable());
    }

    @Test
    void testIsAvailable_WhenInactive() {
        ProductInfoModel product = new ProductInfoModel(1L
                , new BigDecimal("23.99")
                , 10
                , false);
        assertFalse(product.isAvailable());
    }

    @Test
    void testIsAvailable_WhenStockIsZero() {
        ProductInfoModel product = new ProductInfoModel(1L
                , new BigDecimal("344.99")
                , 0
                , true);
        assertFalse(product.isAvailable());
    }

    @Test
    void testIsAvailable_WhenStockIsNull_ShouldThrowException() {
        ProductInfoModel product = new ProductInfoModel(1L
                , new BigDecimal("999.99")
                , null
                , true);
        assertThrows(NullPointerException.class, product::isAvailable);
    }

    @Test
    void testIsAvailable_WhenActiveIsNull_ShouldThrowException() {
        ProductInfoModel product = new ProductInfoModel(1L
                , new BigDecimal("99.99")
                , 5
                , null);
        assertThrows(NullPointerException.class, product::isAvailable);
    }
}
