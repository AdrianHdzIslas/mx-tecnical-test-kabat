package mx.kabat.bff_product.application.service;

import mx.kabat.bff_product.application.port.out.ProductRepositoryPort;
import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private ProductService productService;

    private ProductModel sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new ProductModel(1L
                , "Laptop"
                ,new BigDecimal("100.5")
                , 10
                , true);
    }

    @Test
    void createProduct_shouldReturnSavedProduct() {
        Mockito.when(productRepositoryPort.save(sampleProduct)).thenReturn(sampleProduct);

        ProductModel result = productService.createProduct(sampleProduct);

        Assertions.assertThat(result).isEqualTo(sampleProduct);
        Mockito.verify(productRepositoryPort).save(sampleProduct);
    }

    @Test
    void findAllProducts_shouldReturnListOfProducts() {
        List<ProductModel> products = List.of(sampleProduct);
        Mockito.when(productRepositoryPort.findAllProducts()).thenReturn(products);

        List<ProductModel> result = productService.findAllProducts();

        Assertions.assertThat(result).containsExactly(sampleProduct);
        Mockito.verify(productRepositoryPort).findAllProducts();
    }

    @Test
    void findProduct_shouldReturnProductById() {
        Mockito.when(productRepositoryPort.findById(1L)).thenReturn(Optional.of(sampleProduct));

        ProductModel result = productService.findProduct(1L);

        Assertions.assertThat(result).isEqualTo(sampleProduct);
        Mockito.verify(productRepositoryPort).findById(1L);
    }

    @Test
    void checkAvailability_shouldReturnProductAvailabilityMap() {
        List<Long> ids = List.of(1L, 2L);
        Map<Long, ProductInfoModel> availabilityMap = Map.of(
                1L, new ProductInfoModel(1L,new BigDecimal("100.5"),23, true),
                2L, new ProductInfoModel(2L, new BigDecimal("100.5"),22,false)
        );

        Mockito.when(productRepositoryPort.findByIds(ids)).thenReturn(availabilityMap);

        Map<Long, ProductInfoModel> result = productService.checkAvailability(ids);

        Assertions.assertThat(result).isEqualTo(availabilityMap);
        Mockito.verify(productRepositoryPort).findByIds(ids);
    }

    @Test
    void updateStockProduct_shouldCallRepository() {
        List<SimpleProductInfo> updates = List.of(new SimpleProductInfo(1L, 2));

        productService.updateStockProduct(updates);

        Mockito.verify(productRepositoryPort).updateStockProduct(updates);
    }
}
