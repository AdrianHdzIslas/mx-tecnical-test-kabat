package mx.kabat.bff_product.domain.model;


import java.math.BigDecimal;

public record ProductModel
        (
                Long idProduct,
                String name,
                BigDecimal price,
                Integer stock,
                Boolean active
        ) {
    public boolean isAvailable() {
        return active && stock > 0;
    }

    public ProductModel withUpdatedStock(int change) {
        int newStock = stock + change;
        if (newStock < 0) {
            throw new IllegalStateException("Stock can't be negative");
        }
        return new ProductModel(idProduct, name, price, newStock, active);
    }
}
