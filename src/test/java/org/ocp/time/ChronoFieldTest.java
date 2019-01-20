package org.ocp.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ch5")
class ChronoFieldTest {

    @Test
    void checkValidIntValueOutOfRange() {
        assertThrows(Exception.class, () -> ChronoField.YEAR.checkValidIntValue(Integer.MAX_VALUE));
    }
}
