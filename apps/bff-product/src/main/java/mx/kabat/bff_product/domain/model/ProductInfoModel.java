package mx.kabat.bff_product.domain.model;

import java.math.BigDecimal;

public record ProductInfoModel(Long idProduct, BigDecimal price, Integer stock, Boolean active) {
    public boolean isAvailable() {
        return active && stock > 0 ;
    }
}
