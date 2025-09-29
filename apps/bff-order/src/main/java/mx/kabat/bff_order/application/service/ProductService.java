package mx.kabat.bff_order.application.service;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_order.application.port.in.microservice.UpdateProductPort;
import mx.kabat.bff_order.application.port.out.microservice.ProductMicroServicePort;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements UpdateProductPort {
    private final ProductMicroServicePort productMicroServicePort;
    @Override
    public void updateStock(UpdateStockRequest request) {
        productMicroServicePort.updateStockProduct(request);
    }
}
