package mx.kabat.bff_order.domain.model.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class OrderItemModelTest {

    @Test
    void testRecordCreationAndAccessors() {
        Long idOrderItem = 10L;
        Long idProduct = 100L;
        Integer quantity = 5;
        BigDecimal price = new BigDecimal("99.99");

        OrderItemModel item = new OrderItemModel(idOrderItem, idProduct, quantity, price);

        assertThat(item.idOrderItem()).isEqualTo(idOrderItem);
        assertThat(item.idProduct()).isEqualTo(idProduct);
        assertThat(item.quantity()).isEqualTo(quantity);
        assertThat(item.price()).isEqualByComparingTo(price);
    }

    @Test
    void testEqualsAndHashCode() {
        OrderItemModel item1 = new OrderItemModel(1L
                , 2L
                , 3
                , new BigDecimal("10.00"));
        OrderItemModel item2 = new OrderItemModel(1L
                , 2L
                , 3
                , new BigDecimal("10.00"));
        OrderItemModel item3 = new OrderItemModel(2L
                , 2L
                , 3
                , new BigDecimal("10.00"));

        assertThat(item1).isEqualTo(item2);
        assertThat(item1.hashCode()).isEqualTo(item2.hashCode());
        assertThat(item1).isNotEqualTo(item3);
    }

    @Test
    void testToString() {
        OrderItemModel item = new OrderItemModel(1L
                , 2L
                , 3
                , new BigDecimal("10.00"));
        String toString = item.toString();
        assertThat(toString).contains("idOrderItem=1"
                , "idProduct=2"
                , "quantity=3"
                , "price=10.00");
    }
}
