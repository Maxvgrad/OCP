package org.ocp.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HashSetTest {

    private Set<Integer> set;

    @BeforeEach
    void setUp() {
        set = new HashSet<>();
    }

    @Test
    @Tag("ch3")
    void test() {
        Set<Number> numbers = new HashSet<>();
        numbers.add(75);
        numbers.add(new Integer(86));
        numbers.add(null);
        numbers.add(309L);

        Iterator iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    void keySet() throws Exception {
        Field mapField = set.getClass().getDeclaredField("map");
        mapField.setAccessible(true);

        HashMap<Integer, Object> mapFromHashSet = (HashMap) mapField.get(set);

        assertNotNull(mapFromHashSet);
    }
}
