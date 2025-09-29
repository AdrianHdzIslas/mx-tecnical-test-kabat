package mx.kabat.bff_order.infraestructure.in.controller.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class OrderItemRequestTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        OrderItemRequest request = new OrderItemRequest(1L
                , 100L
                , 3
                , new BigDecimal("15.50"));

        assertThat(request.getIdOrderItem()).isEqualTo(1L);
        assertThat(request.getIdProduct()).isEqualTo(100L);
        assertThat(request.getQuantity()).isEqualTo(3);
        assertThat(request.getPrice()).isEqualByComparingTo("15.50");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        OrderItemRequest request = new OrderItemRequest();
        request.setIdOrderItem(2L);
        request.setIdProduct(200L);
        request.setQuantity(5);
        request.setPrice(new BigDecimal("29.99"));

        assertThat(request.getIdOrderItem()).isEqualTo(2L);
        assertThat(request.getIdProduct()).isEqualTo(200L);
        assertThat(request.getQuantity()).isEqualTo(5);
        assertThat(request.getPrice()).isEqualByComparingTo("29.99");
    }

    @Test
    void testEqualsAndHashCode() {
        OrderItemRequest r1 = new OrderItemRequest(1L
                , 100L
                , 3
                , new BigDecimal("10.00"));
        OrderItemRequest r2 = new OrderItemRequest(1L
                , 100L
                , 3
                , new BigDecimal("10.00"));
        OrderItemRequest r3 = new OrderItemRequest(2L
                , 200L
                , 4
                , new BigDecimal("20.00"));

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    void testToString() {
        OrderItemRequest request = new OrderItemRequest(1L
                , 100L
                , 2
                , new BigDecimal("5.00"));
        String str = request.toString();

        assertThat(str).contains("idOrderItem=1");
        assertThat(str).contains("idProduct=100");
        assertThat(str).contains("quantity=2");
        assertThat(str).contains("price=5.00");
    }
}

