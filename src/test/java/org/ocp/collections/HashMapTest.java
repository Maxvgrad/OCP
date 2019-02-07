package org.ocp.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapTest {

    @Test
    @Tag("ch3")
    @DisplayName("This will throw an exception at runtime because the keys of a TreeMap must be mutually comparable. " +
                         "Here, String, Integer, and Double are not mutually comparable.")
    void comparableTest() {
        Map m = new TreeMap();
        m.put("1", new ArrayList());    //1
        assertThrows(ClassCastException.class, () -> m.put(1, new Object()));    //2
    }

    @Test
    @DisplayName("assertEquals(1, map.size()); //1 in AbstractMap it would be true")
    @Tag("ch3")
    void deleteNullKeyTest() {
        Map<String, String> map = new HashMap<>();
        assertEquals(0, map.size());
        map.put(null, null);
        assertEquals(1, map.size());
        map.remove(null);
        assertEquals(0, map.size()); //1
    }

    @Test
    @DisplayName("")
    @Tag("ch3")
    void notSuchMethodAddTest() {
        Map<String, String> map = new HashMap<>(4);
        //map.add("key", "value");
    }

    @Test
    @DisplayName("")
    @Tag("ch3")
    void mergeTest() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);

        System.out.println(map);

        map.merge(1, 3, (a, b) -> a + b);
        map.merge(3, 3, (a, b) -> a + b);
        System.out.println(map);
    }
}
