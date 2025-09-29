package mx.kabat.bff_order.infraestructure.out.persistence.mapper;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;

import java.util.List;

public class OrderItemMapper {
    public static OrderItemModel toDomain(OrderItemEntity orderItemEntity) {
        return new OrderItemModel(
                orderItemEntity.getIdOrderItem(),
                orderItemEntity.getIdProduct(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getPrice()
        );
    }

    public static List<OrderItemModel> toDomainLists(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities
                .stream()
                .map(OrderItemMapper::toDomain)
                .toList();
    }


    public static OrderItemEntity toEntity(OrderItemModel orderItemModel) {
        return new OrderItemEntity(orderItemModel.idOrderItem()
                , orderItemModel.idProduct()
                , orderItemModel.quantity()
                , orderItemModel.price()
                , null);
    }

    public static List<OrderItemEntity> toEntityList(List<OrderItemModel> orderItemModels) {
        return orderItemModels
                .stream()
                .map(OrderItemMapper::toEntity)
                .toList();
    }
}
