package mx.kabat.bff_product.application.port.in;

import mx.kabat.bff_product.domain.model.ProductModel;

public interface FindProductPort {
    ProductModel findProduct(Long idProduct);
}
