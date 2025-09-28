package mx.kabat.bff_product.application.port.in;


import mx.kabat.bff_product.domain.model.ProductInfoModel;

import java.util.List;
import java.util.Map;


public interface CheckAvailabilityPort {

    Map<Long, ProductInfoModel> checkAvailability(List<Long> listIdProducts);
}
