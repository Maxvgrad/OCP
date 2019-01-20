package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.Period;

class PeriodTest {


    @Test
    void createPeriod() {
        Period p = Period.ofYears(1);

        System.out.println(p);
    }
}
