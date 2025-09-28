package mx.kabat.bff_product.application.port.in;

import mx.kabat.bff_product.domain.model.ProductModel;

public interface CreateProductPort {
    ProductModel createProduct(ProductModel product);
}
