package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DurationTest {


    @Test
    void toStringDays() {
        String d1 = Duration.ofDays(1).toString();
        assertEquals("PT24H", d1);

        String m60 = Duration.ofMinutes(60).toString();
        assertEquals("PT1H", m60);

        String d3 = Duration.ofDays(3).toString();
        assertEquals("PT72H", d3);

        Duration m1 = Duration.ofMinutes(1);
        assertEquals("PT1M", m1.toString());

        assertEquals("PT59S", m1.minus(1, ChronoUnit.SECONDS).toString());

        assertEquals("PT0.999S", m1.minusSeconds(59).minusMillis(1).toString());
    }

    @Test
    void toString1M60S() {

        Duration m1 = Duration.ofMinutes(1);

        Duration s60 = Duration.ofSeconds(60);

        assertEquals(m1.toString(), s60.toString());

        assertFalse(m1.toString() == s60.toString());

    }

    @Test
    void between() {
        Duration d = Duration.between(LocalDateTime.of(2015, Month.SEPTEMBER, 3, 10, 10), LocalDateTime.of(2015, Month.SEPTEMBER, 2, 10, 10));
        assertEquals("PT-24H", d.toString());

        d = Duration.between(LocalDateTime.of(2015, Month.SEPTEMBER, 3, 11, 10), LocalDateTime.of(2015, Month.SEPTEMBER, 2, 10, 10));
        assertEquals("PT-25H", d.toString());
    }
}
