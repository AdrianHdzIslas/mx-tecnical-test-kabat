package mx.kabat.bff_product.application.service;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_product.application.port.in.*;
import mx.kabat.bff_product.application.port.out.ProductRepositoryPort;
import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductPort, GetAllProductPort, FindProductPort, CheckAvailabilityPort, UpdateStockProductPort {


    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public ProductModel createProduct(ProductModel product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public List<ProductModel> findAllProducts() {
        return productRepositoryPort.findAllProducts();
    }

    @Override
    public ProductModel findProduct(Long idProduct) {
        return productRepositoryPort.findById(idProduct).get();
    }


    @Override
    public Map<Long,ProductInfoModel> checkAvailability(List<Long> listIdProducts) {
        return productRepositoryPort.findByIds(listIdProducts);
    }

    @Override
    public void updateStockProduct(List<SimpleProductInfo> simpleProductInfo) {
        productRepositoryPort.updateStockProduct(simpleProductInfo);
    }
}
