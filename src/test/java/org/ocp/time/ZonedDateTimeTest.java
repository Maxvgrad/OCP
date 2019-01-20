package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZonedDateTimeTest {


    @Test
    void test() {
        ZoneId zoneId = ZoneId.of("Europe/London");
        ZonedDateTime zdt1 = ZonedDateTime.of(2019, 1, 19, 22, 0, 0, 0, zoneId);

        LocalDate ld = LocalDate.of(2019, 1, 19);
        LocalTime lt = LocalTime.of(22, 00, 00, 00);

        ZonedDateTime zdt2 = ZonedDateTime.of(ld, lt, zoneId);

        LocalDateTime ldt = LocalDateTime.of(ld, lt);

        ZonedDateTime zdt3 = ZonedDateTime.of(ldt, zoneId);

        assertEquals(zdt1, zdt2);
        assertEquals(zdt2, zdt3);
    }
}
