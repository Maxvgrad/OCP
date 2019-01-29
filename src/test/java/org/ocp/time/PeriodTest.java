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


}
