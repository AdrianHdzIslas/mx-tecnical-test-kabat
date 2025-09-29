package mx.kabat.bff_order.application.port.out.microservice;

import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;

public interface ProductMicroServicePort {
    void updateStockProduct(UpdateStockRequest request);
}
