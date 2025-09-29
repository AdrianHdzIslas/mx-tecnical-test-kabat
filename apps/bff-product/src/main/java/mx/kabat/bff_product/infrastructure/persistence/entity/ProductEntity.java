package mx.kabat.bff_product.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Check(constraints = "stock >= 0")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProduct;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;
}
