package org.ocp.time.temporal;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ValueRange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalAccessorTest {

    private static final LocalTime time = LocalTime.of(22, 15);

    private static final LocalDateTime dateTime = LocalDateTime.now();

    private static final LocalDate januaryData = LocalDate.of(2019, Month.JANUARY, 1);

    @Test
    void isSupportedExpectFalse() {
        assertFalse(time.isSupported(ChronoField.DAY_OF_MONTH));
        assertFalse(time.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
        assertFalse(time.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
        assertFalse(time.isSupported(ChronoField.ALIGNED_WEEK_OF_MONTH));
        assertFalse(time.isSupported(ChronoField.ALIGNED_WEEK_OF_YEAR));
        assertFalse(time.isSupported(ChronoField.DAY_OF_WEEK));
        assertFalse(time.isSupported(ChronoField.DAY_OF_YEAR));
        assertFalse(time.isSupported(ChronoField.DAY_OF_MONTH));
        assertFalse(time.isSupported(ChronoField.EPOCH_DAY));
        assertFalse(time.isSupported(ChronoField.MONTH_OF_YEAR));
        assertFalse(time.isSupported(ChronoField.PROLEPTIC_MONTH));
        assertFalse(time.isSupported(ChronoField.YEAR_OF_ERA));
    }

    @Test
    void isSupportedExpectTrue() {
        assertTrue(time.isSupported(ChronoField.AMPM_OF_DAY));
        assertTrue(time.isSupported(ChronoField.SECOND_OF_MINUTE));
        assertTrue(time.isSupported(ChronoField.CLOCK_HOUR_OF_AMPM));
        assertTrue(time.isSupported(ChronoField.CLOCK_HOUR_OF_DAY));
        assertTrue(time.isSupported(ChronoField.SECOND_OF_DAY));
        assertTrue(time.isSupported(ChronoField.NANO_OF_DAY));
        assertTrue(time.isSupported(ChronoField.MINUTE_OF_DAY));
        assertTrue(time.isSupported(ChronoField.MICRO_OF_SECOND));
        assertTrue(time.isSupported(ChronoField.HOUR_OF_DAY));
        assertTrue(time.isSupported(ChronoField.HOUR_OF_AMPM));
        assertTrue(time.isSupported(ChronoField.MICRO_OF_DAY));
    }


    @Test
    void rangeTest() {
        assertEquals(ValueRange.of(1, 31), januaryData.range(ChronoField.DAY_OF_MONTH));
        assertEquals(ValueRange.of(1, 31), januaryData.range(ChronoField.DAY_OF_YEAR));
    }
}
