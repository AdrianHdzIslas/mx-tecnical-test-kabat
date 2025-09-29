package mx.kabat.bff_order.infraestructure.in.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long idOrder;
    private Date orderDate;
    private String status;
    private String customerFullName;
    private String customerEmail;
    private List<OrderItemRequest> items;
}
