package mx.kabat.bff_product.infrastructure.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateStockRequest {
    List<SimpleProductRequest> products;
}
