package mx.kabat.bff_product.infraestructure.persistence.repository;

import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;

import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;
import mx.kabat.bff_product.infrastructure.persistence.repository.ProductJpaRepository;
import mx.kabat.bff_product.infrastructure.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

    private ProductJpaRepository productJpaRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productJpaRepository = mock(ProductJpaRepository.class);
        productRepository = new ProductRepository(productJpaRepository);
    }

    @Test
    void testSave() {
        ProductModel model = new ProductModel(1L
                , "SD 124 gb"
                , new BigDecimal("100.0")
                , 5
                , true);
        ProductEntity entity = new ProductEntity(1L
                , "Ram 12gb ddr"
                , new BigDecimal("27.0")
                , 5
                , true);

        when(productJpaRepository.save(any())).thenReturn(entity);

        ProductModel result = productRepository.save(model);

        assertNotNull(result);
        assertEquals(model.idProduct(), result.idProduct());
        verify(productJpaRepository).save(any());
    }

    @Test
    void testFindAllProducts() {
        List<ProductEntity> entities = List.of(
                new ProductEntity(1L
                        , "Placa Madre Intel"
                        , new BigDecimal("200")
                        , 5
                        , true),
                new ProductEntity(2L
                        , "Placa madre AMD"
                        , new BigDecimal("210")
                        , 2
                        , false)
        );

        when(productJpaRepository.findAll()).thenReturn(entities);

        List<ProductModel> result = productRepository.findAllProducts();

        assertEquals(2, result.size());
        assertEquals("Placa Madre Intel", result.get(0).name());
        assertEquals("Placa madre AMD", result.get(1).name());
    }

    @Test
    void testFindById_Found() {
        ProductEntity entity = new ProductEntity(1L
                , "Aire comprimido"
                , new BigDecimal("10.0")
                , 5, true);
        when(productJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<ProductModel> result = productRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Aire comprimido", result.get().name());
    }

    @Test
    void testFindById_NotFound() {
        when(productJpaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProductModel> result = productRepository.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindByIds() {
        List<ProductEntity> entities = List.of(
                new ProductEntity(1L
                        , "Mouse I"
                        , new BigDecimal("20")
                        , 5, true),
                new ProductEntity(2L
                        , "Mouse A"
                        , new BigDecimal("10")
                        , 0
                        , false)
        );

        when(productJpaRepository.findAllById(List.of(1L, 2L))).thenReturn(entities);

        Map<Long, ProductInfoModel> result = productRepository.findByIds(List.of(1L, 2L));

        assertEquals(2, result.size());
        assertTrue(result.containsKey(1L));
        assertEquals(0, result.get(2L).stock());
    }

    @Test
    void testUpdateStockProduct() {
        List<SimpleProductInfo> updates = List.of(
                new SimpleProductInfo(1L, -2),
                new SimpleProductInfo(2L, +3)
        );

        List<ProductEntity> existingEntities = List.of(
                new ProductEntity(1L
                        , "Ventilador"
                        , new BigDecimal("10")
                        , 5, true),
                new ProductEntity(2L
                        , "Ventilador RGB"
                        , new BigDecimal("20")
                        , 1, true)
        );

        when(productJpaRepository.findAllById(Set.of(1L, 2L))).thenReturn(existingEntities);

        productRepository.updateStockProduct(updates);

        ArgumentCaptor<List<ProductEntity>> captor = ArgumentCaptor.forClass(List.class);
        verify(productJpaRepository).saveAll(captor.capture());

        List<ProductEntity> updated = captor.getValue();
        assertEquals(2, updated.size());

        assertEquals(3, updated.get(0).getStock());
        assertEquals(4, updated.get(1).getStock());
    }
}
