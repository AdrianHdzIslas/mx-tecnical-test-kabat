package mx.kabat.bff_order.infraestructure.out.microservice;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;

import mx.kabat.bff_order.domain.model.external.SimpleProductRequest;
import mx.kabat.bff_order.domain.model.external.UpdateStockRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ProductRestAdapterTest {

    private RestTemplate restTemplate;

    private ProductRestAdapter adapter;

    private final String baseUrl = "http://product-service:8081";

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        adapter = new ProductRestAdapter(restTemplate, baseUrl);
    }

    @Test
    void testUpdateStockProduct_CallsRestTemplateWithCorrectParams() {
        SimpleProductRequest product1 = new SimpleProductRequest(1L, 5);
        SimpleProductRequest product2 = new SimpleProductRequest(2L, 3);
        UpdateStockRequest request = new UpdateStockRequest(List.of(product1, product2));

        when(restTemplate.exchange(
                eq(baseUrl + "/stock"),
                eq(HttpMethod.PATCH),
                any(HttpEntity.class),
                eq(Void.class)
        )).thenReturn(ResponseEntity.ok().build());

        assertThatNoException().isThrownBy(() -> adapter.updateStockProduct(request));

        verify(restTemplate, times(1)).exchange(
                eq(baseUrl + "/stock"),
                eq(HttpMethod.PATCH),
                argThat(entity -> {
                    UpdateStockRequest body = (UpdateStockRequest) entity.getBody();
                    return body != null && body.products().size() == 2;
                }),
                eq(Void.class)
        );
    }
}

