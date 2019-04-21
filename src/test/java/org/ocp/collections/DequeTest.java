package org.ocp.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DequeTest {

    private Deque<String> deque = new ArrayDeque<>();

    @Test
    void pop() {
        deque.add("one");
        deque.push("two");
        assertEquals("two", deque.pop());
        assertEquals("one", deque.pop());
    }

    @Test
    void peek() {
        deque.add("one");
        deque.push("two");
        assertEquals("two", deque.peek());
        assertEquals("two", deque.peekFirst());
        assertEquals("one", deque.peekLast());
    }

    @Test
    void poll() {
        deque.add("one");
        deque.push("two");
        deque.addLast("three");
        assertEquals("two", deque.poll());
        assertEquals("three", deque.pollLast());
        assertEquals("one", deque.pop());
    }

    @Test
    void add() {
        deque.add("one");
        deque.push("two");
        deque.add("three"); // addLast
        assertEquals("two", deque.poll());
        assertEquals("three", deque.pollLast());
        assertEquals("one", deque.pop());
    }

    @Test
    void offer() {
        deque.add("one");
        deque.push("two");
        deque.offer("three"); // addLast
        assertEquals("two", deque.poll());
        assertEquals("three", deque.pollLast());
        assertEquals("one", deque.pop());
    }
    @Test
    void offerLast() {
        deque.offerFirst("one"); // addFirst
        deque.push("two");
        deque.offerLast("three"); // addLast
        assertEquals("two", deque.poll());
        assertEquals("three", deque.pollLast());
        assertEquals("one", deque.pop());
    }

    @Test
    void remove() {
        deque.push("1");
        deque.push("2");

        assertEquals("2", deque.remove());


    }
}
