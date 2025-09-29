package mx.kabat.bff_order.infraestructure.in.controller.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderItemRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderItemResponse;
import org.junit.jupiter.api.Test;

public class OrderItemMapperTest {

    @Test
    void testToDomain() {
        OrderItemRequest request = new OrderItemRequest(1L
                , 101L, 3, new BigDecimal("29.99"));

        OrderItemModel model = OrderItemMapper.toDomain(request);

        assertThat(model).isNotNull();
        assertThat(model.idOrderItem()).isEqualTo(1L);
        assertThat(model.idProduct()).isEqualTo(101L);
        assertThat(model.quantity()).isEqualTo(3);
        assertThat(model.price()).isEqualByComparingTo("29.99");
    }

    @Test
    void testToDomainList() {
        OrderItemRequest r1 = new OrderItemRequest(1L
                , 101L, 2, new BigDecimal("10.00"));
        OrderItemRequest r2 = new OrderItemRequest(2L
                , 102L, 1, new BigDecimal("20.00"));
        List<OrderItemRequest> requests = List.of(r1, r2);

        List<OrderItemModel> models = OrderItemMapper.toDomainList(requests);

        assertThat(models).hasSize(2);
        assertThat(models.get(0).idProduct()).isEqualTo(101L);
        assertThat(models.get(1).quantity()).isEqualTo(1);
    }

    @Test
    void testToResponse() {
        OrderItemModel model = new OrderItemModel(5L
                , 205L, 4, new BigDecimal("15.00"));

        OrderItemResponse response = OrderItemMapper.toResponse(model);

        assertThat(response).isNotNull();
        assertThat(response.getIdOrderItem()).isEqualTo(5L);
        assertThat(response.getIdProduct()).isEqualTo(205L);
        assertThat(response.getQuantity()).isEqualTo(4);
        assertThat(response.getPrice()).isEqualByComparingTo("15.00");
    }

    @Test
    void testToResponseList() {
        OrderItemModel m1 = new OrderItemModel(1L
                , 101L, 2, new BigDecimal("9.99"));
        OrderItemModel m2 = new OrderItemModel(2L
                , 102L, 1, new BigDecimal("5.00"));
        List<OrderItemModel> models = List.of(m1, m2);

        List<OrderItemResponse> responses = OrderItemMapper.toResponseList(models);

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getIdProduct()).isEqualTo(101L);
        assertThat(responses.get(1).getPrice()).isEqualByComparingTo("5.00");
    }
}

