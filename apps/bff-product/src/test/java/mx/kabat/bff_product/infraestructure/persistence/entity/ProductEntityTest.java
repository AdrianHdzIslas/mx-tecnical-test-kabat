package mx.kabat.bff_product.infraestructure.persistence.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductEntityTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void testPersistValidProduct() {
        ProductEntity product = new ProductEntity(
                null,
                "Laptop",
                new BigDecimal("1500.00"),
                10,
                true
        );

        entityManager.persist(product);
        entityManager.flush();

        assertNotNull(product.getIdProduct());
    }

    @Test
    @Transactional
    void testPersistProductWithNegativeStock_ShouldFail() {
        ProductEntity product = new ProductEntity(
                null,
                "Mouse",
                new BigDecimal("25.00"),
                -5,
                true
        );

        Exception exception = assertThrows(Exception.class, () -> {
            entityManager.persist(product);
            entityManager.flush();
        });

        String message = exception.getMessage();
        assertTrue(message.contains("check constraint") || message.toLowerCase().contains("stock"));
    }
}

