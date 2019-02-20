package org.ocp.concurrent.atomic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicIntegerTest {

    private AtomicInteger atomicInteger;

    @BeforeEach
    void setUp() {
        atomicInteger = new AtomicInteger();
    }

    @Test
    void lazySet() {
        assertEquals(0, atomicInteger.get());
        atomicInteger.lazySet(10);
        assertEquals(10, atomicInteger.get());
    }

    @Test
    void getAndAdd() {
        assertEquals(0, atomicInteger.getAndIncrement());
        assertEquals(1, atomicInteger.get());
    }

    @Test
    void incrementAndGet() {
        assertEquals(1, atomicInteger.incrementAndGet());
        assertEquals(1, atomicInteger.get());
    }

    @Test
    void updateAndGet() {
        assertEquals(0, atomicInteger.getAndUpdate(cur -> cur + 15));
    }

    @Test
    void getAndUpdate() {
        assertEquals(15, atomicInteger.updateAndGet(cur -> cur + 15));
    }

}
