package mx.kabat.bff_order.application.service;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_order.application.port.in.db.CreateOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetAllOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetOrderPort;
import mx.kabat.bff_order.application.port.out.db.OrderRepositoryPort;
import mx.kabat.bff_order.application.port.out.microservice.ProductMicroServicePort;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.infraestructure.out.microservice.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderPort, GetOrderPort, GetAllOrderPort {

    private final OrderRepositoryPort orderRepository;
    private final ProductMicroServicePort productMicroServicePort;

    @Override
    @Transactional
    public OrderModel createOrder(OrderModel order) {
        UpdateStockRequest updateStockRequest = ProductMapper.toDomain(order);
        productMicroServicePort.updateStockProduct(updateStockRequest);
        return orderRepository.save(order);
    }

    @Override
    public OrderModel findByID(Long idOrder) {
        return orderRepository.findById(idOrder);
    }

    @Override
    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }
}
