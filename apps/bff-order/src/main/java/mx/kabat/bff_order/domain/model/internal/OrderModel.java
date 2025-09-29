package mx.kabat.bff_order.domain.model.internal;

import java.util.Date;
import java.util.List;

public record OrderModel(Long idOrder,
                         List<OrderItemModel> items,
                         Date orderDate,
                         OrderStatus orderStatus,
                         String customerFullName,
                         String customerEmail) {
}
