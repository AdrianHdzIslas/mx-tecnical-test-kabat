package mx.kabat.bff_product.infrastructure.controller.dto;

import lombok.Data;

@Data
public class SimpleProductRequest {
    private Long idProduct;
    private Integer amount;
}
