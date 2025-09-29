package mx.kabat.bff_product.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProductRequest {
    private Long idProduct;
    private Integer amount;
}
