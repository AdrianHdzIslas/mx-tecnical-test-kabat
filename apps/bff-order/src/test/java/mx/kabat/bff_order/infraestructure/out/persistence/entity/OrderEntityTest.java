package mx.kabat.bff_order.infraestructure.out.persistence.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class OrderEntityTest {

    @Test
    void testOrderEntityGettersAndSetters() {
        OrderItemEntity item1 = new OrderItemEntity();
        OrderItemEntity item2 = new OrderItemEntity();

        OrderEntity order = new OrderEntity();
        order.setIdOrder(1L);
        order.setItems(Arrays.asList(item1, item2));
        order.setOrderDate(new Date());
        order.setStatus("PENDING");
        order.setCustomerFullName("Juan Perez");
        order.setCustomerEmail("juan@gmail.com");

        assertThat(order.getIdOrder()).isEqualTo(1L);
        assertThat(order.getItems()).hasSize(2);
        assertThat(order.getStatus()).isEqualTo("PENDING");
        assertThat(order.getCustomerFullName()).isEqualTo("Juan Perez");
        assertThat(order.getCustomerEmail()).isEqualTo("juan@gmail.com");
        assertThat(order.getOrderDate()).isNotNull();
    }
}
