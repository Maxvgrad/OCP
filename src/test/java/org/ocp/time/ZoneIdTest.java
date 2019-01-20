package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneIdTest {

    @Test
    void test() {
        System.out.println(ZoneId.of("America/Los_Angeles"));
    }

    @Test
    void testCurrentZoneId() {
        System.out.println(ZoneId.from(ZonedDateTime.now()));
    }

    @Test
    void getAvailableZoneIds() {
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }
}
