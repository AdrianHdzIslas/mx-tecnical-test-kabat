package mx.kabat.bff_order.infraestructure.in.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class OrderItemResponseTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        OrderItemResponse response = new OrderItemResponse(1L
                , 100L, 2, new BigDecimal("19.99"));

        assertThat(response.getIdOrderItem()).isEqualTo(1L);
        assertThat(response.getIdProduct()).isEqualTo(100L);
        assertThat(response.getQuantity()).isEqualTo(2);
        assertThat(response.getPrice()).isEqualByComparingTo("19.99");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        OrderItemResponse response = new OrderItemResponse();
        response.setIdOrderItem(2L);
        response.setIdProduct(200L);
        response.setQuantity(5);
        response.setPrice(new BigDecimal("29.99"));

        assertThat(response.getIdOrderItem()).isEqualTo(2L);
        assertThat(response.getIdProduct()).isEqualTo(200L);
        assertThat(response.getQuantity()).isEqualTo(5);
        assertThat(response.getPrice()).isEqualByComparingTo("29.99");
    }

    @Test
    void testEqualsAndHashCode() {
        OrderItemResponse r1 = new OrderItemResponse(1L
                , 100L, 2, new BigDecimal("10.00"));
        OrderItemResponse r2 = new OrderItemResponse(1L
                , 100L, 2, new BigDecimal("10.00"));
        OrderItemResponse r3 = new OrderItemResponse(2L
                , 200L, 3, new BigDecimal("20.00"));

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    void testToString() {
        OrderItemResponse response = new OrderItemResponse(1L
                , 100L, 2, new BigDecimal("5.00"));
        String str = response.toString();

        assertThat(str).contains("idOrderItem=1");
        assertThat(str).contains("idProduct=100");
        assertThat(str).contains("quantity=2");
        assertThat(str).contains("price=5.00");
    }
}

