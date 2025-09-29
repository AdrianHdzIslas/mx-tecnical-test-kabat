package mx.kabat.bff_order.domain.model.external;


import java.util.List;

public record UpdateStockRequest (List<SimpleProductRequest> products){
}
