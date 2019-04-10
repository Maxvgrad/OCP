package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * From Java 1.5 onwards, you can also use a new
 * Concurrent library available in java.java.util.concurrent package,
 * which contains interfaces/classes such as ConcurrentMap/ConcurrentHashMap.
 * Classes in this package offer better performance than objects returned from
 * Collections.synchronizedXXX methods.
 */
class ConcurrentHashMapTest {

    private ConcurrentHashMap map;

    @Test //if (key == null || value == null) throw new NullPointerException();
    void insertNull() {
        map = new ConcurrentHashMap();
        assertThrows(NullPointerException.class, () -> map.put(null, ""));
        assertThrows(NullPointerException.class, () -> map.put("key", null));
    }
}
