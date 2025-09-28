package mx.kabat.bff_product.application.port.in;

import mx.kabat.bff_product.domain.model.ProductModel;

import java.util.List;

public interface GetAllProductPort {
    List<ProductModel> findAllProducts();
}
