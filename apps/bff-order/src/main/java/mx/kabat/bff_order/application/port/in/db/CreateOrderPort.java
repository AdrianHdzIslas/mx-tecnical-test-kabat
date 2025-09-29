package mx.kabat.bff_order.application.port.in.db;

import mx.kabat.bff_order.domain.model.internal.OrderModel;

public interface CreateOrderPort {
    OrderModel createOrder(OrderModel order);
}
