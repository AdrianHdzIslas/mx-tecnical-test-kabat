package mx.kabat.bff_order.infraestructure.out.persistence.mapper;


import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderEntity;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;

import java.util.List;

public class OrderMapper {

    public static OrderModel toDomain(OrderEntity orderEntity) {
        List<OrderItemModel> orderItemModels = OrderItemMapper.toDomainLists(orderEntity.getItems());
        OrderStatus status = OrderStatus.valueOf(orderEntity.getStatus());
        return new OrderModel(
                orderEntity.getIdOrder()
                , orderItemModels
                , orderEntity.getOrderDate()
                , status
                , orderEntity.getCustomerFullName()
                , orderEntity.getCustomerEmail());
    }

    public static OrderEntity toEntity(OrderModel orderModel) {
        List<OrderItemEntity> orderItems = OrderItemMapper.toEntityList(orderModel.items());
        return new OrderEntity(
                orderModel.idOrder(),
                orderItems,
                orderModel.orderDate(),
                orderModel.orderStatus().toString(),
                orderModel.customerFullName(),
                orderModel.customerEmail()
        );
    }
}
