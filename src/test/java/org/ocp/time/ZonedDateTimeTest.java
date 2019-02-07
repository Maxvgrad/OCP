package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

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

    // DayLight Savings time

    @Test
    void marchChangeOver() {

        assertMarchChangeOver(LocalDate.of(2015, 3, 8));
        assertMarchChangeOver(LocalDate.of(2016, 3, 13));
        assertMarchChangeOver(LocalDate.of(2017, 3, 12));
        assertMarchChangeOver(LocalDate.of(2018, 3, 11));
        assertMarchChangeOver(LocalDate.of(2019, 3, 10));
        assertMarchChangeOver(LocalDate.of(2020, 3, 8));

    }

    private void assertMarchChangeOver(LocalDate localDate) {

        LocalTime localTime = LocalTime.of(1, 59);

        ZoneId zoneId = ZoneId.of("America/Chicago");

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);

        zonedDateTime = zonedDateTime.plus(1, ChronoUnit.MINUTES);

        assertEquals(3, zonedDateTime.getHour());
    }

    @Test
    void marchBetween() {
        LocalDate ld = LocalDate.of(2019, Month.MARCH, 10);
        LocalTime lt = LocalTime.of(1, 30);

        ZoneId zoneId = ZoneId.of("US/Eastern");

        ZonedDateTime oneHourZoneDateTime = ZonedDateTime.of(ld, lt, zoneId);
        ZonedDateTime threeHourDateTime = oneHourZoneDateTime.plus(1, ChronoUnit.HOURS);

        assertEquals(1, ChronoUnit.HOURS.between(oneHourZoneDateTime, threeHourDateTime));
        assertEquals(-1, threeHourDateTime.until(oneHourZoneDateTime, ChronoUnit.HOURS));
        assertEquals(-1, ChronoUnit.HOURS.between(threeHourDateTime, oneHourZoneDateTime));
        assertEquals(2, threeHourDateTime.getHour() - oneHourZoneDateTime.getHour());
    }

    @Test
    void novemberChangeOver() {

        assertNovemberChangeOver(LocalDate.of(2016, Month.NOVEMBER, 6));
        assertNovemberChangeOver(LocalDate.of(2017, Month.NOVEMBER, 5));
        assertNovemberChangeOver(LocalDate.of(2018, Month.NOVEMBER, 4));
        assertNovemberChangeOver(LocalDate.of(2019, Month.NOVEMBER, 3));

    }

    private void assertNovemberChangeOver(LocalDate localDate) {

        LocalTime localTime = LocalTime.of(1, 59);

        ZoneId zoneId = ZoneId.of("America/Chicago");

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);

        zonedDateTime = zonedDateTime.plus(1, ChronoUnit.MINUTES);

        assertEquals(1, zonedDateTime.getHour());
    }
}
