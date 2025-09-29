package mx.kabat.bff_order.domain.model.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderStatusTest {

    @Test
    void testEnumValues() {
        OrderStatus[] statuses = OrderStatus.values();
        assertThat(statuses).containsExactly(OrderStatus.PENDING, OrderStatus.CONFIRMED, OrderStatus.CANCELLED);
    }

    @Test
    void testValueOfValid() {
        assertThat(OrderStatus.valueOf("PENDING")).isEqualTo(OrderStatus.PENDING);
        assertThat(OrderStatus.valueOf("CONFIRMED")).isEqualTo(OrderStatus.CONFIRMED);
        assertThat(OrderStatus.valueOf("CANCELLED")).isEqualTo(OrderStatus.CANCELLED);
    }

    @Test
    void testValueOfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> OrderStatus.valueOf("INVALID_STATUS"));
    }
}