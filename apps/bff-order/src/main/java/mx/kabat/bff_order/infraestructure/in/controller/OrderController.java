package mx.kabat.bff_order.infraestructure.in.controller;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_order.application.port.in.db.CreateOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetAllOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetOrderPort;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.response.OrderResponse;
import mx.kabat.bff_order.infraestructure.in.controller.mapper.OrderMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderPort createOrderPort;
    private final GetOrderPort getOrderPort;
    private final GetAllOrderPort getAllOrderPort;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderModel orderModel = OrderMapper.toDomain(orderRequest);
        OrderModel createOrderResult = createOrderPort.createOrder(orderModel);
        return ResponseEntity.ok(OrderMapper.toResponse(createOrderResult));
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<OrderResponse> createOrders(@PathVariable Long idOrder) {
        OrderModel orderModel = getOrderPort.findByID(idOrder);
        OrderResponse orderResponse = OrderMapper.toResponse(orderModel);
        return ResponseEntity.ok(orderResponse);
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderModel> listModel = getAllOrderPort.findAll();
        List<OrderResponse> listResponse = listModel.stream().map(OrderMapper::toResponse).toList();
        return ResponseEntity.ok(listResponse);
    }

}
