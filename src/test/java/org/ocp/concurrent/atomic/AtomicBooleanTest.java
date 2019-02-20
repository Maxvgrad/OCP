package org.ocp.concurrent.atomic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtomicBooleanTest {

    private AtomicBoolean atomicBoolean;

    @BeforeEach
    void setUp() {
        atomicBoolean = new AtomicBoolean();
    }

    @Test
    void get() {
        assertFalse(atomicBoolean.get());
    }

    @Test
    void compareAndSet() {
        atomicBoolean.compareAndSet(atomicBoolean.get(), true);
        assertTrue(atomicBoolean.get());
    }

    @Test
    void valuesOffset12L() throws Exception {
        Field field = atomicBoolean.getClass().getDeclaredField("valueOffset");
        field.setAccessible(true);

        long offset = field.getLong(atomicBoolean);

        assertEquals(12L, offset);
    }
}
