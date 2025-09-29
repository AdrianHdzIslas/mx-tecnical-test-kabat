package mx.kabat.bff_product.application.port.out;

import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepositoryPort {
    ProductModel save(ProductModel product);
    List<ProductModel> findAllProducts();
    Optional<ProductModel> findById(Long idProduct);
    Map<Long, ProductInfoModel> findByIds(List<Long> idProducts);
    void updateStockProduct(List<SimpleProductInfo> simpleProductInfo);
}
