package org.ocp.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockingDequeTest {

    private BlockingDeque<Integer> blockingDeque;

    @BeforeEach
    void setUp() {
        blockingDeque = new LinkedBlockingDeque<>();
    }

    @Test
    void offer() {
        blockingDeque.offer(1);

        assertEquals(Integer.valueOf(1), blockingDeque.peek());
        assertEquals(1, blockingDeque.size());
        assertEquals(Integer.valueOf(1), blockingDeque.poll());
        assertEquals(0, blockingDeque.size());
    }


    @Test // ch7q14
    void poolFirst() throws InterruptedException {
        blockingDeque.offer(1);

        assertEquals(Integer.valueOf(1), blockingDeque.pollFirst(1, TimeUnit.SECONDS));
        assertEquals(0, blockingDeque.size());
    }


}
