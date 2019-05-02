package org.ocp.concurrent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReentrantLockTest {

    private ReentrantLock lock;

    @BeforeEach
    void setUp() {
        lock = new ReentrantLock();
    }

    @AfterEach
    void tearDown() {
        System.out.println(lock.isLocked());
        if (lock.isLocked())
            lock.unlock();
    }

    @Test
    void tryLock() {
        lock.lock();
        assertTrue(lock.tryLock());
    }

    @Test
    void isLocked() {
        assertFalse(lock.isLocked());
    }
}
