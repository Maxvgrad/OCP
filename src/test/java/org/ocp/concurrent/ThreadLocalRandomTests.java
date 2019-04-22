package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

class ThreadLocalRandomTests {


    @Test
    void name() {
        ThreadLocalRandom.current().nextInt(1, 10);
    }
}
