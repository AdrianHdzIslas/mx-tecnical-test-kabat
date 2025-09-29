package mx.kabat.bff_order.infraestructure.out.microservice.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.kabat.bff_order.domain.model.external.SimpleProductRequest;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import org.junit.jupiter.api.Test;

public class ProductMapperTest {

    @Test
    void testToDomain_OrderItemModel() {
        OrderItemModel itemModel = new OrderItemModel(100L
                , 200L
                , 3
                , new BigDecimal("9.99"));

        SimpleProductRequest result = ProductMapper.toDomain(itemModel);

        assertThat(result).isNotNull();
        assertThat(result.idProduct()).isEqualTo(200L);
        assertThat(result.amount()).isEqualTo(-3);
    }

    @Test
    void testToDomain_OrderModel() {
        OrderItemModel item1 = new OrderItemModel(1L
                , 111L
                , 2
                , new BigDecimal("5.00"));
        OrderItemModel item2 = new OrderItemModel(2L
                , 222L
                , 4
                , new BigDecimal("3.00"));
        List<OrderItemModel> items = List.of(item1, item2);

        OrderModel orderModel = new OrderModel(
                1L,
                items,
                new Date(),
                OrderStatus.CONFIRMED,
                "El islas",
                "el.islas.dev@gmail.com.mx"
        );

        UpdateStockRequest stockRequest = ProductMapper.toDomain(orderModel);

        assertThat(stockRequest).isNotNull();
        assertThat(stockRequest.products()).hasSize(2);

        SimpleProductRequest product1 = stockRequest.products().get(0);
        SimpleProductRequest product2 = stockRequest.products().get(1);

        assertThat(product1.idProduct()).isEqualTo(111L);
        assertThat(product1.amount()).isEqualTo(-2);

        assertThat(product2.idProduct()).isEqualTo(222L);
        assertThat(product2.amount()).isEqualTo(-4);
    }
}

