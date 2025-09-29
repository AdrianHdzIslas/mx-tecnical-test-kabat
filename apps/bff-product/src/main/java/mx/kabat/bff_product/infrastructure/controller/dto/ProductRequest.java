package mx.kabat.bff_product.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Long idProduct;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;
}
