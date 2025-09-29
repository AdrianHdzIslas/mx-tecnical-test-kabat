package mx.kabat.bff_order.infraestructure.in.controller.mapper;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderItemRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderItemResponse;

import java.util.List;

public class OrderItemMapper {

    public static OrderItemModel toDomain(OrderItemRequest orderItemRequest) {
        return new OrderItemModel(
                orderItemRequest.getIdOrderItem(),
                orderItemRequest.getIdProduct(),
                orderItemRequest.getQuantity(),
                orderItemRequest.getPrice()
        );
    }

    public static List<OrderItemModel> toDomainList(List<OrderItemRequest> orderItemRequests) {
        return orderItemRequests.stream().map(OrderItemMapper::toDomain).toList();
    }

    public static OrderItemResponse toResponse(OrderItemModel orderItemModel) {
        return new OrderItemResponse(
                orderItemModel.idOrderItem(),
                orderItemModel.idProduct(),
                orderItemModel.quantity(),
                orderItemModel.price()
        );
    }

    public static List<OrderItemResponse> toResponseList(List<OrderItemModel> orderItemModels) {
        return orderItemModels.stream().map(OrderItemMapper::toResponse).toList();
    }
}
