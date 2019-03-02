package org.ocp.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConcurrentSkipListSetTest {

    private ConcurrentSkipListSet<Integer> concurrentSkipListSet;

    @BeforeEach
    void setUp() {
        concurrentSkipListSet = new ConcurrentSkipListSet<>();
    }

    @Test
    void add() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        concurrentSkipListSet.addAll(list);
        assertEquals(3, concurrentSkipListSet.size());

        for (Integer elem : concurrentSkipListSet) {
            concurrentSkipListSet.add(5);
        }
        assertEquals(4, concurrentSkipListSet.size());
    }
}
