package mx.kabat.bff_order.infraestructure.out.microservice.mapper;

import mx.kabat.bff_order.domain.model.external.SimpleProductRequest;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;

import java.util.List;

public class ProductMapper {

    public static SimpleProductRequest toDomain(OrderItemModel orderItemModel) {
        return new SimpleProductRequest(
                orderItemModel.idProduct(),
                orderItemModel.quantity() * -1
        );
    }


    public static UpdateStockRequest toDomain(OrderModel order) {
        List<SimpleProductRequest> simpleProductRequests = order
                .items()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
        return new UpdateStockRequest(simpleProductRequests);
    }
}
