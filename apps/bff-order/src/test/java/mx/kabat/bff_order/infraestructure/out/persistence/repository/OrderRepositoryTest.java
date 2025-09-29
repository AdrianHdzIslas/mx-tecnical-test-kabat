package mx.kabat.bff_order.infraestructure.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderEntity;
import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


public class OrderRepositoryTest {


    private OrderJpaRepository orderJpaRepository;

    private OrderRepository orderRepository;

    private OrderModel orderModel;
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderJpaRepository = mock(OrderJpaRepository.class);
        orderRepository = new OrderRepository(orderJpaRepository);
        OrderItemModel itemModel = new OrderItemModel(1L
                , 101L
                , 2
                , new BigDecimal("10.00"));
        orderModel = new OrderModel(
                1L,
                List.of(itemModel),
                new Date(),
                OrderStatus.PENDING,
                "Adrian Hdz",
                "aislas@gmail.com"
        );

        OrderItemEntity itemEntity = new OrderItemEntity(1L
                , 101L
                , 2
                , new BigDecimal("10.00")
                , null);
        orderEntity = new OrderEntity(
                1L,
                List.of(itemEntity),
                orderModel.orderDate(),
                "PENDING",
                "Adrian Hdz",
                "aislas@gmail.com"
        );
    }

    @Test
    void testSave() {
        when(orderJpaRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderModel saved = orderRepository.save(orderModel);

        assertThat(saved).isNotNull();
        assertThat(saved.idOrder()).isEqualTo(1L);
        assertThat(saved.orderStatus()).isEqualTo(OrderStatus.PENDING);

        verify(orderJpaRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void testFindById_Found() {
        when(orderJpaRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

        OrderModel result = orderRepository.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.idOrder()).isEqualTo(1L);
        assertThat(result.customerEmail()).isEqualTo("aislas@gmail.com");

        verify(orderJpaRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(orderJpaRepository.findById(999L)).thenReturn(Optional.empty());

        OrderModel result = orderRepository.findById(999L);

        assertThat(result).isNull();

        verify(orderJpaRepository).findById(999L);
    }

    @Test
    void testFindAll() {
        when(orderJpaRepository.findAll()).thenReturn(List.of(orderEntity));

        List<OrderModel> result = orderRepository.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).idOrder()).isEqualTo(1L);

        verify(orderJpaRepository).findAll();
    }
}
