package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalTimeTest {

    @Test
    void minusHour() {
        LocalTime lt = LocalTime.of(22, 00, 15);
        lt = lt.minus(1, ChronoUnit.HOURS);
        assertEquals(21, lt.getHour());
    }

    @Test
    void minusMinutes() {
        LocalTime lt = LocalTime.of(22, 0, 15, 0);
        lt = lt.minus(1, ChronoUnit.MINUTES);
        assertEquals(21, lt.getHour());
        assertEquals(59, lt.getMinute());
    }

    @Test
    void minusSecond() {
        LocalTime lt = LocalTime.of(22, 0, 15, 0);
        lt = lt.minus(1, ChronoUnit.SECONDS);
        assertEquals(14, lt.getSecond());
    }

    @Test
    void minusNanos() {
        LocalTime lt = LocalTime.of(22, 0, 15, 0);
        lt = lt.minus(1, ChronoUnit.NANOS);
        assertEquals(14, lt.getSecond());
        assertEquals(999999999, lt.getNano());
    }

}
