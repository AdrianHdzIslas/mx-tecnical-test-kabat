package mx.kabat.bff_order.infraestructure.in.controller.mapper;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderItemResponse;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderResponse;


import java.util.List;

public class OrderMapper {

    public static OrderModel toDomain(OrderRequest orderRequest) {
        List<OrderItemModel> items = OrderItemMapper.toDomainList(orderRequest.getItems());
        OrderStatus status = OrderStatus.valueOf(orderRequest.getStatus());
        return new OrderModel(
                orderRequest.getIdOrder(),
                items,
                orderRequest.getOrderDate(),
                status,
                orderRequest.getCustomerFullName(),
                orderRequest.getCustomerEmail()

        );
    }

    public static OrderResponse toResponse(OrderModel orderModel) {
        List<OrderItemResponse> orderItemResponses  = OrderItemMapper.toResponseList(orderModel.items());
        return new OrderResponse(
                orderModel.idOrder(),
                orderModel.orderDate(),
                orderModel.orderStatus().toString(),
                orderModel.customerFullName(),
                orderModel.customerEmail(),
                orderItemResponses
        );
    }
}
