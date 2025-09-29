package mx.kabat.bff_order.domain.model.internal;

import java.math.BigDecimal;

public record OrderItemModel(Long idOrderItem,
                             Long idProduct,
                             Integer quantity,
                             BigDecimal price) {
}
