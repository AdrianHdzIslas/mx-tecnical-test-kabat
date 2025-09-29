package mx.kabat.bff_order.infraestructure.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;
import org.junit.jupiter.api.Test;

public class OrderItemMapperTest {

    @Test
    void testToDomain() {
        OrderItemEntity entity = new OrderItemEntity(1L
                , 101L
                , 2
                , new BigDecimal("19.99")
                , null);

        OrderItemModel model = OrderItemMapper.toDomain(entity);

        assertThat(model.idOrderItem()).isEqualTo(1L);
        assertThat(model.idProduct()).isEqualTo(101L);
        assertThat(model.quantity()).isEqualTo(2);
        assertThat(model.price()).isEqualByComparingTo("19.99");
    }

    @Test
    void testToDomainLists() {
        OrderItemEntity entity1 = new OrderItemEntity(1L
                , 101L
                , 2
                , new BigDecimal("19.99")
                , null);
        OrderItemEntity entity2 = new OrderItemEntity(2L
                , 102L
                , 3
                , new BigDecimal("29.99")
                , null);
        List<OrderItemEntity> entityList = List.of(entity1, entity2);

        List<OrderItemModel> modelList = OrderItemMapper.toDomainLists(entityList);

        assertThat(modelList).hasSize(2);
        assertThat(modelList.get(0).idProduct()).isEqualTo(101L);
        assertThat(modelList.get(1).idProduct()).isEqualTo(102L);
    }

    @Test
    void testToEntity() {
        OrderItemModel model = new OrderItemModel(1L
                , 101L
                , 2
                , new BigDecimal("19.99"));

        OrderItemEntity entity = OrderItemMapper.toEntity(model);

        assertThat(entity.getIdOrderItem()).isEqualTo(1L);
        assertThat(entity.getIdProduct()).isEqualTo(101L);
        assertThat(entity.getQuantity()).isEqualTo(2);
        assertThat(entity.getPrice()).isEqualByComparingTo("19.99");
        assertThat(entity.getOrder()).isNull();
    }

    @Test
    void testToEntityList() {
        OrderItemModel model1 = new OrderItemModel(1L
                , 101L
                , 2
                , new BigDecimal("19.99"));
        OrderItemModel model2 = new OrderItemModel(2L
                , 102L
                , 3
                , new BigDecimal("29.99"));
        List<OrderItemModel> modelList = List.of(model1, model2);

        List<OrderItemEntity> entityList = OrderItemMapper.toEntityList(modelList);

        assertThat(entityList).hasSize(2);
        assertThat(entityList.get(0).getIdProduct()).isEqualTo(101L);
        assertThat(entityList.get(1).getIdProduct()).isEqualTo(102L);
    }
}

