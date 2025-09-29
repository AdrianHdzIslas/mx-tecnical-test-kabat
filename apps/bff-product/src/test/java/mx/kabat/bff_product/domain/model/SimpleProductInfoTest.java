package mx.kabat.bff_product.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleProductInfoTest {

    @Test
    void testRecordFields() {
        Long id = 10L;
        Integer amount = 3;

        SimpleProductInfo info = new SimpleProductInfo(id, amount);

        assertEquals(id, info.idProduct());
        assertEquals(amount, info.amount());
    }

    @Test
    void testEqualsAndHashCode() {
        SimpleProductInfo info1 = new SimpleProductInfo(1L, 5);
        SimpleProductInfo info2 = new SimpleProductInfo(1L, 5);
        SimpleProductInfo info3 = new SimpleProductInfo(2L, 10);

        assertEquals(info1, info2);
        assertNotEquals(info1, info3);
        assertEquals(info1.hashCode(), info2.hashCode());
    }

    @Test
    void testToString() {
        SimpleProductInfo info = new SimpleProductInfo(1L, 5);
        String expected = "SimpleProductInfo[idProduct=1, amount=5]";
        assertEquals(expected, info.toString());
    }
}
