package mx.kabat.bff_order.application.port.out.db;

import mx.kabat.bff_order.domain.model.internal.OrderModel;

import java.util.List;

public interface OrderRepositoryPort {
    OrderModel save(OrderModel order);
    OrderModel findById(Long id);
    List<OrderModel> findAll();
}
