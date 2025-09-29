package mx.kabat.bff_order.infraestructure.in.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long idOrder;
    private Date orderDate;
    private String status;
    private String customerFullName;
    private String customerEmail;
    private List<OrderItemResponse> items;
}
