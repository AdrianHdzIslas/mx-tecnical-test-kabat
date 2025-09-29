package mx.kabat.bff_order.domain.model.external;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class UpdateStockRequestTest {

    @Test
    void testRecordCreationAndAccessors() {
        SimpleProductRequest product1 = new SimpleProductRequest(1L, 10);
        SimpleProductRequest product2 = new SimpleProductRequest(2L, 20);
        List<SimpleProductRequest> products = List.of(product1, product2);

        UpdateStockRequest request = new UpdateStockRequest(products);

        assertThat(request.products()).hasSize(2).containsExactly(product1, product2);
    }

    @Test
    void testEqualsAndHashCode() {
        List<SimpleProductRequest> products1 = List.of(new SimpleProductRequest(1L, 10));
        List<SimpleProductRequest> products2 = List.of(new SimpleProductRequest(1L, 10));
        List<SimpleProductRequest> products3 = List.of(new SimpleProductRequest(2L, 5));

        UpdateStockRequest req1 = new UpdateStockRequest(products1);
        UpdateStockRequest req2 = new UpdateStockRequest(products2);
        UpdateStockRequest req3 = new UpdateStockRequest(products3);

        assertThat(req1).isEqualTo(req2);
        assertThat(req1.hashCode()).isEqualTo(req2.hashCode());
        assertThat(req1).isNotEqualTo(req3);
    }

    @Test
    void testToString() {
        SimpleProductRequest product = new SimpleProductRequest(1L, 10);
        UpdateStockRequest request = new UpdateStockRequest(List.of(product));
        String toString = request.toString();

        assertThat(toString).contains("products=[");
        assertThat(toString).contains("idProduct=1");
        assertThat(toString).contains("amount=10");
    }
}
