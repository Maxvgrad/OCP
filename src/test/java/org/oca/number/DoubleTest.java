package org.oca.number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleTest {


    @Test
    void isNaN() {
        assertTrue(Double.isNaN(Double.NaN));
        double nan = Double.longBitsToDouble(0x7ff8000000000000L);
        assertTrue(Double.isNaN(nan));

        assertTrue(Double.isNaN(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY));
        assertTrue(Double.isNaN(Double.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY));
        assertTrue(Double.isNaN(Math.sqrt(-1)));
        assertTrue(Double.isNaN(Math.log(-1)));
    }
}
