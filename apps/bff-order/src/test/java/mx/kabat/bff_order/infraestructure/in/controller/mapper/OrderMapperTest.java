package mx.kabat.bff_order.infraestructure.in.controller.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderItemRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderResponse;
import org.junit.jupiter.api.Test;

public class OrderMapperTest {

    @Test
    void testToDomain() {
        OrderItemRequest itemRequest = new OrderItemRequest(1L
                , 101L, 2, new BigDecimal("9.99"));
        List<OrderItemRequest> itemRequests = List.of(itemRequest);

        OrderRequest orderRequest = new OrderRequest(
                10L,
                new Date(),
                "PENDING",
                "Carlos",
                "carlos@gamil.com",
                itemRequests
        );

        OrderModel model = OrderMapper.toDomain(orderRequest);

        assertThat(model).isNotNull();
        assertThat(model.idOrder()).isEqualTo(10L);
        assertThat(model.orderStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(model.customerFullName()).isEqualTo("Carlos");
        assertThat(model.items()).hasSize(1);
        assertThat(model.items().get(0).idProduct()).isEqualTo(101L);
    }

    @Test
    void testToResponse() {
        OrderItemModel itemModel = new OrderItemModel(2L
                , 202L, 3, new BigDecimal("19.99"));
        List<OrderItemModel> itemModels = List.of(itemModel);

        OrderModel orderModel = new OrderModel(
                20L,
                itemModels,
                new Date(),
                OrderStatus.CONFIRMED,
                "Laura",
                "laura@gamil.com"
        );

        OrderResponse response = OrderMapper.toResponse(orderModel);

        assertThat(response).isNotNull();
        assertThat(response.getIdOrder()).isEqualTo(20L);
        assertThat(response.getStatus()).isEqualTo("CONFIRMED");
        assertThat(response.getCustomerEmail()).isEqualTo("laura@gamil.com");
        assertThat(response.getItems()).hasSize(1);
        assertThat(response.getItems().get(0).getIdProduct()).isEqualTo(202L);
    }
}

