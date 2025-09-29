package mx.kabat.bff_product.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductModelTest {

    private final ProductModel baseProduct = new ProductModel(
            1L,
            "Lap top test",
            new BigDecimal("100.00"),
            10,
            true
    );

    @Test
    void testIsAvailable_WhenActiveAndStockPositive() {
        assertTrue(baseProduct.isAvailable());
    }

    @Test
    void testIsAvailable_WhenInactive() {
        ProductModel inactiveProduct = new ProductModel(1L
                , "Lap top test"
                , new BigDecimal("10.00")
                , 10
                , false);
        assertFalse(inactiveProduct.isAvailable());
    }

    @Test
    void testIsAvailable_WhenStockZero() {
        ProductModel outOfStock = new ProductModel(1L
                , "Lap top test"
                , new BigDecimal("10.00")
                , 0
                , true);
        assertFalse(outOfStock.isAvailable());
    }

    @Test
    void testWithUpdatedStock_PositiveChange() {
        ProductModel updated = baseProduct.withUpdatedStock(5);
        assertEquals(15, updated.stock());
    }

    @Test
    void testWithUpdatedStock_ZeroChange() {
        ProductModel updated = baseProduct.withUpdatedStock(0);
        assertEquals(10, updated.stock());
    }

    @Test
    void testWithUpdatedStock_NegativeButValidChange() {
        ProductModel updated = baseProduct.withUpdatedStock(-5);
        assertEquals(5, updated.stock());
    }

    @Test
    void testWithUpdatedStock_ExceedsStock_ShouldThrowException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            baseProduct.withUpdatedStock(-15);
        });

        assertEquals("Stock can't be negative", exception.getMessage());
    }
}
