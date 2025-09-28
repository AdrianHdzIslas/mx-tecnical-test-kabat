package mx.kabat.bff_product.infrastructure.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class CheckProductRequest {
    private List<Long> products;
}
