package mx.kabat.bff_order.domain.model.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OrderModelTest {

    @Test
    void testRecordCreationAndAccessors() {
        OrderItemModel item1 = new OrderItemModel(1L
                , 10L
                , 2
                , new java.math.BigDecimal("20.00"));
        OrderItemModel item2 = new OrderItemModel(2L
                , 11L
                , 1
                , new java.math.BigDecimal("10.00"));
        List<OrderItemModel> items = Arrays.asList(item1, item2);
        Date orderDate = new Date();
        OrderStatus status = OrderStatus.PENDING;
        String customerName = "Ana Gomez";
        String customerEmail = "ana@gmail.com";

        OrderModel order = new OrderModel(100L
                , items
                , orderDate
                , status
                , customerName
                , customerEmail);

        assertThat(order.idOrder()).isEqualTo(100L);
        assertThat(order.items()).hasSize(2).containsExactly(item1, item2);
        assertThat(order.orderDate()).isEqualTo(orderDate);
        assertThat(order.orderStatus()).isEqualTo(status);
        assertThat(order.customerFullName()).isEqualTo(customerName);
        assertThat(order.customerEmail()).isEqualTo(customerEmail);
    }

    @Test
    void testEqualsAndHashCode() {
        OrderItemModel item1 = new OrderItemModel(1L
                , 10L
                , 2
                , new java.math.BigDecimal("20.00"));
        List<OrderItemModel> items = List.of(item1);
        Date orderDate = new Date();
        OrderStatus status = OrderStatus.PENDING;
        OrderModel order1 = new OrderModel(1L, items
                , orderDate
                , status
                , "Adrian ISlas"
                , "aislas@gamail.com");
        OrderModel order2 = new OrderModel(1L
                , items
                , orderDate
                , status
                , "Adrian ISlas"
                , "aislas@gamail.com");
        OrderModel order3 = new OrderModel(2L
                , items
                , orderDate
                , status
                , "Juan Gabriel"
                , "jgabriel@hotmail.com");

        assertThat(order1).isEqualTo(order2);
        assertThat(order1.hashCode()).isEqualTo(order2.hashCode());
        assertThat(order1).isNotEqualTo(order3);
    }

    @Test
    void testToString() {
        OrderItemModel item = new OrderItemModel(1L
                , 10L
                , 2
                , new java.math.BigDecimal("20.00"));
        OrderModel order = new OrderModel(1L
                , List.of(item)
                , new Date()
                , OrderStatus.PENDING
                , "Pedro reyes"
                , "p.reyes@empressa.mx");
        String toString = order.toString();

        assertThat(toString).contains("idOrder=1");
        assertThat(toString).contains("items=[");
        assertThat(toString).contains("orderStatus=PENDING");
        assertThat(toString).contains("customerFullName=Pedro reyes");
        assertThat(toString).contains("customerEmail=p.reyes@empressa.mx");
    }
}
