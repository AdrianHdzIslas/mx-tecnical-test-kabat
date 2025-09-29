package mx.kabat.bff_order.infraestructure.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderEntity;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;
import org.junit.jupiter.api.Test;

public class OrderMapperTest {

    @Test
    void testToDomain() {
        OrderItemEntity item1 = new OrderItemEntity(1L
                , 101L
                , 2
                , new BigDecimal("19.99")
                , null);
        OrderItemEntity item2 = new OrderItemEntity(2L
                , 102L
                , 1
                , new BigDecimal("9.99")
                , null);
        List<OrderItemEntity> items = List.of(item1, item2);

        OrderEntity entity = new OrderEntity(
                10L,
                items,
                new Date(),
                "CONFIRMED",
                "Luis Mendoza",
                "luis@live.com"
        );

        OrderModel model = OrderMapper.toDomain(entity);

        assertThat(model.idOrder()).isEqualTo(10L);
        assertThat(model.items()).hasSize(2);
        assertThat(model.orderStatus()).isEqualTo(OrderStatus.CONFIRMED);
        assertThat(model.customerFullName()).isEqualTo("Luis Mendoza");
        assertThat(model.customerEmail()).isEqualTo("luis@live.com");
        assertThat(model.orderDate()).isEqualTo(entity.getOrderDate());
    }

    @Test
    void testToEntity() {
        OrderItemModel item1 = new OrderItemModel(1L, 101L, 2, new BigDecimal("19.99"));
        OrderItemModel item2 = new OrderItemModel(2L, 102L, 1, new BigDecimal("9.99"));
        List<OrderItemModel> items = List.of(item1, item2);

        OrderModel model = new OrderModel(
                20L,
                items,
                new Date(),
                OrderStatus.PENDING,
                "Carla Torres",
                "carla@hotmail.com"
        );

        OrderEntity entity = OrderMapper.toEntity(model);

        assertThat(entity.getIdOrder()).isEqualTo(20L);
        assertThat(entity.getItems()).hasSize(2);
        assertThat(entity.getStatus()).isEqualTo("PENDING");
        assertThat(entity.getCustomerFullName()).isEqualTo("Carla Torres");
        assertThat(entity.getCustomerEmail()).isEqualTo("carla@hotmail.com");
        assertThat(entity.getOrderDate()).isEqualTo(model.orderDate());
    }
}

