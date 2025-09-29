package mx.kabat.bff_order.infraestructure.out.persistence.repository;

import mx.kabat.bff_order.infraestructure.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
