package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTest {

    @Test
    void test() {
        LocalDate localDate1 = LocalDate.of(2019, 1, 1);

        LocalDate localDate2 = LocalDate.of(2019, Month.JANUARY, 1);

        assertEquals(localDate1, localDate2);
    }

    @Test
    void plusDay() {
        LocalDate ld = LocalDate.of(2019, Month.FEBRUARY, 14);
        ld = ld.plus(1, ChronoUnit.DAYS);
        assertEquals(15, ld.getDayOfMonth());
    }

    @Test
    void plusMonth() {
        LocalDate ld = LocalDate.of(2019, Month.FEBRUARY, 14);
        ld = ld.plus(2, ChronoUnit.MONTHS);
        assertEquals(Month.APRIL, ld.getMonth());
    }

    @Test
    void plusYear() {
        LocalDate ld = LocalDate.of(2019, Month.FEBRUARY, 14);
        ld = ld.plus(2, ChronoUnit.YEARS);
        assertEquals(2021, ld.getYear());
    }

    @Test
    void plusMonthToJanuary() {
        LocalDate ld = LocalDate.of(2019, Month.JANUARY, 30);
        ld = ld.plus(1, ChronoUnit.MONTHS);
        assertEquals(28, ld.getDayOfMonth());
    }


    @Test
    void formatter() {
        LocalDate localDate = LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
        localDate.plusDays(2);
        //localDate.plusHours(5);
        assertEquals(30, localDate.getDayOfMonth());
    }


}
