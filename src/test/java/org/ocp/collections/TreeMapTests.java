package org.ocp.collections;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreeMapTests {

    private TreeMap map = new TreeMap();


    @Test
    void put() {
        map.put("1", new Object());
        map.put(1, new Object());
    }

    @Test
    void putNull() {
        assertThrows(NullPointerException.class, () -> map.put(null, new Object()));
    }
}
