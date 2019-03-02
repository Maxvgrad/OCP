package org.ocp.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import static org.junit.jupiter.api.Assertions.assertNull;

class CounterActionTest {

    private ForkJoinPool forkJoinPool;

    @BeforeEach
    void setUp() {
        forkJoinPool = new ForkJoinPool();
    }

    @Test //ch7q12
    void compute() {
        ForkJoinTask<?> task = new CounterAction(0, 4);

        Object obj = forkJoinPool.invoke(new CounterAction(0, 4));

        assertNull(obj);
    }
}