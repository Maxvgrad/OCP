package org.ocp.collections;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueInterfaceTest {

    private Queue<String> queue = new LinkedList<>();

    @Test
    void offer() {
        queue.offer("offer");
        assertEquals(1, queue.size());
    }

    @Test
    void poll() {
        queue.offer("offer");
        assertEquals("offer", queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void pollNull() {
        assertNull(queue.poll());
    }

    @Test
    void peek() {
        queue.offer("offer");
        assertEquals("offer", queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    void peekNull() {
        assertNull(queue.peek());
    }

    @Test
    void element() {
        queue.offer("offer");
        assertEquals("offer", queue.element());
        assertFalse(queue.isEmpty());
    }

    @Test
    void elementFirst() {
        queue.offer("first");
        queue.offer("second");
        assertEquals("first", queue.element());
        assertFalse(queue.isEmpty());
    }

    @Test
    void elementThrows() {
        assertThrows(NoSuchElementException.class, queue::element);
    }

    @Test
    void remove() {
        queue.offer("offer");
        assertEquals("offer", queue.remove());
        assertTrue(queue.isEmpty());
    }

    @Test
    void removeThrows() {
        assertThrows(NoSuchElementException.class, () -> queue.remove());
    }
}
