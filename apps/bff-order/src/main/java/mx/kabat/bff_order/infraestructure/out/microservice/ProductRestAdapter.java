package mx.kabat.bff_order.infraestructure.out.microservice;

import mx.kabat.bff_order.application.port.out.microservice.ProductMicroServicePort;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductRestAdapter implements ProductMicroServicePort {

    private final RestTemplate restTemplate;
    private final String url;

    public ProductRestAdapter(RestTemplate restTemplate,
                              @Value("${external.product-service.base-url}")
                              String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public void updateStockProduct(UpdateStockRequest request) {
        restTemplate.exchange(url.concat("/stock")
                , HttpMethod.PATCH
                , new HttpEntity<UpdateStockRequest>(request)
                , Void.class);
    }
}
