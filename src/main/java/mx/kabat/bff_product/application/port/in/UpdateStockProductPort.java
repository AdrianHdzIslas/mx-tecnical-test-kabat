package mx.kabat.bff_product.application.port.in;

import mx.kabat.bff_product.domain.model.SimpleProductInfo;

import java.util.List;

public interface UpdateStockProductPort {
    void updateStockProduct(List<SimpleProductInfo> simpleProductInfo);
}
