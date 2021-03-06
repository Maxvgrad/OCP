package org.ocp.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalTest {

    @Test
    void flatMapTest() {
        Optional<Integer> flat = Optional.of(4).flatMap(Optional::of);
        assertEquals(Optional.class, flat.getClass());
    }


    @Test
    void mapTest() {
        Optional<Integer> flat = Optional.<Integer>of(4).map(Function.identity());
        assertEquals(Optional.class, flat.getClass());
    }

    @Test
    void ofStatic() {
        Optional empty = Optional.empty();
        assertTrue(empty.of(new Object()).isPresent());

        assertEquals(Optional.empty(), empty);
    }
}
