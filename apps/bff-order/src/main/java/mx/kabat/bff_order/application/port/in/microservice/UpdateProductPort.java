package mx.kabat.bff_order.application.port.in.microservice;

import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;

public interface UpdateProductPort {
    void updateStock(UpdateStockRequest request);
}
