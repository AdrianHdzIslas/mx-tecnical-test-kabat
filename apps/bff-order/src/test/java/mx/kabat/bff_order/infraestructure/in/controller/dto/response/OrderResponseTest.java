package mx.kabat.bff_order.infraestructure.in.controller.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderResponseTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        Date date = new Date();
        OrderItemResponse item = new OrderItemResponse(1L
                , 100L, 2, new BigDecimal("19.99"));
        List<OrderItemResponse> items = List.of(item);

        OrderResponse response = new OrderResponse(
                10L,
                date,
                "CONFIRMED",
                "Adrian Hdz",
                "aislas@gmail.com",
                items
        );

        assertThat(response.getIdOrder()).isEqualTo(10L);
        assertThat(response.getOrderDate()).isEqualTo(date);
        assertThat(response.getStatus()).isEqualTo("CONFIRMED");
        assertThat(response.getCustomerFullName()).isEqualTo("Adrian Hdz");
        assertThat(response.getCustomerEmail()).isEqualTo("aislas@gmail.com");
        assertThat(response.getItems()).containsExactly(item);
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        OrderResponse response = new OrderResponse();

        Date date = new Date();
        OrderItemResponse item = new OrderItemResponse(2L
                , 200L, 1, new BigDecimal("9.99"));
        List<OrderItemResponse> items = List.of(item);

        response.setIdOrder(20L);
        response.setOrderDate(date);
        response.setStatus("PENDING");
        response.setCustomerFullName("Luis Mendoza");
        response.setCustomerEmail("luis@example.com");
        response.setItems(items);

        assertThat(response.getIdOrder()).isEqualTo(20L);
        assertThat(response.getOrderDate()).isEqualTo(date);
        assertThat(response.getStatus()).isEqualTo("PENDING");
        assertThat(response.getCustomerFullName()).isEqualTo("Luis Mendoza");
        assertThat(response.getCustomerEmail()).isEqualTo("luis@example.com");
        assertThat(response.getItems()).containsExactly(item);
    }

    @Test
    void testEqualsAndHashCode() {
        Date date = new Date();
        OrderItemResponse item = new OrderItemResponse(3L, 300L, 5, new BigDecimal("50.00"));
        List<OrderItemResponse> items = List.of(item);

        OrderResponse r1 = new OrderResponse(1L
                , date, "CONFIRMED", "A", "a@mail.com", items);
        OrderResponse r2 = new OrderResponse(1L
                , date, "CONFIRMED", "A", "a@mail.com", items);
        OrderResponse r3 = new OrderResponse(2L
                , date, "CANCELLED", "B", "b@mail.com", items);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    void testToString() {
        Date date = new Date();
        OrderItemResponse item = new OrderItemResponse(4L
                , 400L, 3, new BigDecimal("30.00"));
        List<OrderItemResponse> items = List.of(item);

        OrderResponse response = new OrderResponse(99L
                , date, "PENDING", "Carlos", "carlos@mail.com", items);

        String str = response.toString();

        assertThat(str).contains("idOrder=99");
        assertThat(str).contains("status=PENDING");
        assertThat(str).contains("customerFullName=Carlos");
        assertThat(str).contains("customerEmail=carlos@mail.com");
        assertThat(str).contains("items=[");
    }
}
