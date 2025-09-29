package mx.kabat.bff_product.infraestructure.persistence.mapper;

import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;
import mx.kabat.bff_product.infrastructure.persistence.mapper.ProductMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void testToDomain() {
        ProductEntity entity = new ProductEntity(
                1L,
                "Impresora",
                new BigDecimal("999.99"),
                5,
                true
        );

        ProductModel model = ProductMapper.toDomain(entity);

        assertEquals(entity.getIdProduct(), model.idProduct());
        assertEquals(entity.getName(), model.name());
        assertEquals(entity.getPrice(), model.price());
        assertEquals(entity.getStock(), model.stock());
        assertEquals(entity.getActive(), model.active());
    }

    @Test
    void testToEntity() {
        ProductModel model = new ProductModel(
                2L,
                "HDD 2.0",
                new BigDecimal("49.99"),
                10,
                false
        );

        ProductEntity entity = ProductMapper.toEntity(model);

        assertEquals(model.idProduct(), entity.getIdProduct());
        assertEquals(model.name(), entity.getName());
        assertEquals(model.price(), entity.getPrice());
        assertEquals(model.stock(), entity.getStock());
        assertEquals(model.active(), entity.getActive());
    }

    @Test
    void testToDomainInfo() {
        ProductEntity entity = new ProductEntity(
                3L,
                "Power Bank",
                new BigDecimal("19.99"),
                7,
                true
        );

        ProductInfoModel infoModel = ProductMapper.toDomainInfo(entity);

        assertEquals(entity.getIdProduct(), infoModel.idProduct());
        assertEquals(entity.getPrice(), infoModel.price());
        assertEquals(entity.getStock(), infoModel.stock());
        assertEquals(entity.getActive(), infoModel.active());
    }
}

