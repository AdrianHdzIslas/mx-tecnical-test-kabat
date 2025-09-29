package mx.kabat.bff_order.infraestructure.in.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long idOrderItem;
    private Long idProduct;
    private Integer quantity;
    private BigDecimal price;
}
