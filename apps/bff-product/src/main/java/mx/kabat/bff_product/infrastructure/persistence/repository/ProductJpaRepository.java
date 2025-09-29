package mx.kabat.bff_product.infrastructure.persistence.repository;

import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
