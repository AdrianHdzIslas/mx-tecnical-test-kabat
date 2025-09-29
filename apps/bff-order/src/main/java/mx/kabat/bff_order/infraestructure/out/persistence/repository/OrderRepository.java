package mx.kabat.bff_order.infraestructure.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_order.application.port.out.db.OrderRepositoryPort;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderEntity;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;
import mx.kabat.bff_order.infraestructure.out.persistence.mapper.OrderItemMapper;
import mx.kabat.bff_order.infraestructure.out.persistence.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public OrderModel save(OrderModel orderModel) {
        OrderEntity orderEntity = OrderMapper.toEntity(orderModel);

        List<OrderItemEntity> itemEntities = orderModel.items().stream()
                .map(itemModel -> {
                    OrderItemEntity item = OrderItemMapper.toEntity(itemModel);
                    item.setOrder(orderEntity);
                    return item;
                })
                .toList();

        orderEntity.setItems(itemEntities);
        OrderEntity result = orderJpaRepository.save(orderEntity);
        return OrderMapper.toDomain(result);
    }

    @Override
    public OrderModel findById(Long id) {
        OrderEntity orderEntity = orderJpaRepository.findById(id).orElse(null);
        return OrderMapper.toDomain(orderEntity);
    }

    @Override
    public List<OrderModel> findAll() {
        List<OrderEntity> orderEntities = orderJpaRepository.findAll();
        return orderEntities.stream().map(OrderMapper::toDomain).toList();
    }
}
