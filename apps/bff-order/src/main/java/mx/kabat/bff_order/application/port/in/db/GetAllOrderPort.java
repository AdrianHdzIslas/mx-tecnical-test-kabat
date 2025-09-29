package mx.kabat.bff_order.application.port.in.db;

import mx.kabat.bff_order.domain.model.internal.OrderModel;

import java.util.List;

public interface GetAllOrderPort {
    List<OrderModel> findAll();
}
