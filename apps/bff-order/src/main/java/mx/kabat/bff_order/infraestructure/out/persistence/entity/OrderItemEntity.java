package mx.kabat.bff_order.infraestructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idOrderItem;
    private Long idProduct;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity order;
}
