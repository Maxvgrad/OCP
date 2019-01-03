package org.ocp.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapTest {

    @Test
    @DisplayName("This will throw an exception at runtime because the keys of a TreeMap must be mutually comparable. " +
                         "Here, String, Integer, and Double are not mutually comparable.")
    void test() {
        Map m = new TreeMap();
        m.put("1", new ArrayList());    //1
        assertThrows(ClassCastException.class, () -> m.put(1, new Object()));    //2
    }
}
