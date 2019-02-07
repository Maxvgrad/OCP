package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodTest {


    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

    @Test
    void createPeriod() {
        Period p = Period.ofYears(1);

        System.out.println(p);
    }

    @Test
    void periodThreeArgsConstructor() {
        LocalDateTime ldt = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
        Period p = Period.of(1, 2, 3);

        ldt = ldt.minus(p);

        assertEquals("11:22", TIME_FORMATTER.format(ldt));
    }

    @Test
    void toStringPeriod() {

        Period d1 = Period.ofDays(1);

        assertEquals("P1D", d1.toString());

        assertEquals("P32D", d1.plusDays(31).toString());

        Period y2m43d500 = Period.ofDays(500).plusMonths(43).plusYears(2);

        assertEquals("P2Y43M500D", y2m43d500.toString());

    }
}
