package mx.kabat.bff_order.domain.model.external;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SimpleProductRequestTest {

    @Test
    void testRecordCreationAndAccessors() {
        SimpleProductRequest request = new SimpleProductRequest(123L, 10);

        assertThat(request.idProduct()).isEqualTo(123L);
        assertThat(request.amount()).isEqualTo(10);
    }

    @Test
    void testEqualsAndHashCode() {
        SimpleProductRequest req1 = new SimpleProductRequest(1L, 5);
        SimpleProductRequest req2 = new SimpleProductRequest(1L, 5);
        SimpleProductRequest req3 = new SimpleProductRequest(2L, 5);

        assertThat(req1).isEqualTo(req2);
        assertThat(req1.hashCode()).isEqualTo(req2.hashCode());
        assertThat(req1).isNotEqualTo(req3);
    }

    @Test
    void testToString() {
        SimpleProductRequest request = new SimpleProductRequest(1L, 5);
        String toString = request.toString();

        assertThat(toString).contains("idProduct=1");
        assertThat(toString).contains("amount=5");
    }
}
