package org.ocp.collections;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    @Test
    @Tag("ch3")
    void removeMethodTest() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(12);
        queue.remove(1);
        assertEquals(2, queue.size());
    }
}
