package mx.kabat.bff_order.infraestructure.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.kabat.bff_order.application.port.in.db.CreateOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetAllOrderPort;
import mx.kabat.bff_order.application.port.in.db.GetOrderPort;
import mx.kabat.bff_order.domain.model.internal.OrderItemModel;
import mx.kabat.bff_order.domain.model.internal.OrderModel;
import mx.kabat.bff_order.domain.model.internal.OrderStatus;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderItemRequest;
import mx.kabat.bff_order.infraestructure.in.controller.dto.request.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private CreateOrderPort createOrderPort;

    @Mock
    private GetOrderPort getOrderPort;

    @Mock
    private GetAllOrderPort getAllOrderPort;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private OrderRequest orderRequest;
    private OrderModel orderModel;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();

        OrderItemRequest itemRequest = new OrderItemRequest(1L, 101L, 2, new BigDecimal("9.99"));
        orderRequest = new OrderRequest(
                1L,
                new Date(),
                "PENDING",
                "Carlos",
                "test@gmail.com",
                List.of(itemRequest)
        );

        OrderItemModel itemModel = new OrderItemModel(1L, 101L, 2, new BigDecimal("9.99"));
        orderModel = new OrderModel(
                1L,
                List.of(itemModel),
                orderRequest.getOrderDate(),
                OrderStatus.PENDING,
                "Carlos",
                "test@gmail.com"
        );
    }

    @Test
    void testCreateOrder() throws Exception {
        when(createOrderPort.createOrder(orderModel)).thenReturn(orderModel);

        mockMvc.perform(post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idOrder").value(1L))
                .andExpect(jsonPath("$.customerFullName").value("Carlos"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.items[0].idProduct").value(101L));
    }

    @Test
    void testFindById() throws Exception {
        when(getOrderPort.findByID(1L)).thenReturn(orderModel);

        mockMvc.perform(get("/api/v1/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idOrder").value(1L))
                .andExpect(jsonPath("$.customerEmail").value("test@gmail.com"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void testFindAll() throws Exception {
        when(getAllOrderPort.findAll()).thenReturn(List.of(orderModel));

        mockMvc.perform(get("/api/v1/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idOrder").value(1L))
                .andExpect(jsonPath("$[0].customerFullName").value("Carlos"));
    }
}

