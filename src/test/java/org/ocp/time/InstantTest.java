package org.ocp.time;

import org.junit.jupiter.api.Test;

import java.time.Instant;

public class InstantTest {

    @Test
    void test() {
        long epochSeconds = 0;

        Instant.now();
        Instant.ofEpochSecond(epochSeconds);


    }
}
