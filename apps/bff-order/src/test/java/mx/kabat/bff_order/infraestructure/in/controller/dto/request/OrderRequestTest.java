package mx.kabat.bff_order.infraestructure.in.controller.dto.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderRequestTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        Date date = new Date();
        OrderItemRequest item = new OrderItemRequest(1L
                , 101L
                , 2
                , new BigDecimal("19.99"));
        List<OrderItemRequest> items = List.of(item);

        OrderRequest request = new OrderRequest(
                10L,
                date,
                "PENDING",
                "Adrian Islas",
                "asilas@gmail.com",
                items
        );

        assertThat(request.getIdOrder()).isEqualTo(10L);
        assertThat(request.getOrderDate()).isEqualTo(date);
        assertThat(request.getStatus()).isEqualTo("PENDING");
        assertThat(request.getCustomerFullName()).isEqualTo("Adrian Islas");
        assertThat(request.getCustomerEmail()).isEqualTo("asilas@gmail.com");
        assertThat(request.getItems()).hasSize(1);
        assertThat(request.getItems().get(0).getIdProduct()).isEqualTo(101L);
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        OrderRequest request = new OrderRequest();

        Date date = new Date();
        OrderItemRequest item = new OrderItemRequest(2L
                , 202L
                , 1
                , new BigDecimal("9.99"));
        List<OrderItemRequest> items = List.of(item);

        request.setIdOrder(20L);
        request.setOrderDate(date);
        request.setStatus("CONFIRMED");
        request.setCustomerFullName("Adrian Islas");
        request.setCustomerEmail("asilas@gmail.com");
        request.setItems(items);

        assertThat(request.getIdOrder()).isEqualTo(20L);
        assertThat(request.getOrderDate()).isEqualTo(date);
        assertThat(request.getStatus()).isEqualTo("CONFIRMED");
        assertThat(request.getCustomerFullName()).isEqualTo("Adrian Islas");
        assertThat(request.getCustomerEmail()).isEqualTo("asilas@gmail.com");
        assertThat(request.getItems()).hasSize(1);
        assertThat(request.getItems().get(0).getIdProduct()).isEqualTo(202L);
    }

    @Test
    void testEqualsAndHashCode() {
        Date date = new Date();
        OrderItemRequest item = new OrderItemRequest(1L
                , 101L
                , 2
                , new BigDecimal("19.99"));
        List<OrderItemRequest> items = List.of(item);

        OrderRequest r1 = new OrderRequest(1L
                , date
                , "PENDING"
                , "Juan"
                , "juan@mail.com"
                , items);
        OrderRequest r2 = new OrderRequest(1L
                , date
                , "PENDING"
                , "Juan"
                , "juan@mail.com"
                , items);
        OrderRequest r3 = new OrderRequest(2L
                , date
                , "CANCELLED"
                , "Ana"
                , "ana@mail.com"
                , items);

        assertThat(r1).isEqualTo(r2);
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    void testToString() {
        OrderItemRequest item = new OrderItemRequest(1L
                , 101L
                , 2
                , new BigDecimal("19.99"));
        List<OrderItemRequest> items = List.of(item);
        Date date = new Date();

        OrderRequest request = new OrderRequest(1L
                , date
                , "PENDING"
                , "Carlos"
                , "carlos@mail.com"
                , items);

        String str = request.toString();
        assertThat(str).contains("idOrder=1");
        assertThat(str).contains("status=PENDING");
        assertThat(str).contains("customerFullName=Carlos");
        assertThat(str).contains("customerEmail=carlos@mail.com");
    }
}

