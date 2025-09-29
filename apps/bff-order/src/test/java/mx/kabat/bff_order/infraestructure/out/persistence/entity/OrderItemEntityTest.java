package mx.kabat.bff_order.infraestructure.out.persistence.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class OrderItemEntityTest {

    @Test
    void testOrderItemEntityGettersAndSetters() {
        OrderEntity order = new OrderEntity();
        order.setIdOrder(1L);

        OrderItemEntity item = new OrderItemEntity();
        item.setIdOrderItem(10L);
        item.setIdProduct(100L);
        item.setQuantity(5);
        item.setPrice(new BigDecimal("99.99"));
        item.setOrder(order);

        assertThat(item.getIdOrderItem()).isEqualTo(10L);
        assertThat(item.getIdProduct()).isEqualTo(100L);
        assertThat(item.getQuantity()).isEqualTo(5);
        assertThat(item.getPrice()).isEqualByComparingTo(new BigDecimal("99.99"));
        assertThat(item.getOrder()).isNotNull();
        assertThat(item.getOrder().getIdOrder()).isEqualTo(1L);
    }
}

