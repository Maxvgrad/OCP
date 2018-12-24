package org.ocp.ch3;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class ArrayListTest {

    private static List<String> testList;

    @BeforeAll
    static void init() {
        testList = new ArrayList<String>();
        testList.add("hello");
        testList.add("world");
        testList.add("Max");
        testList.add("Joan");
        testList.add("hotspot");
        testList.add("lock");
        testList.add("jon");
        testList.add("cap");
        testList.add("goal");
        testList.add("java");
    }

    @Test
    void toArrayTest() {
        assertEquals(10, testList.size());
        String[] arr = testList.toArray(new String[20]);
        assertEquals(20, arr.length);
        assertNotNull(arr[9]);
        assertNull(arr[10]);
        assertNull(arr[11]);

        String cast = castMethod(arr, 4);

        assertNotNull(cast);

    }

    <T> T castMethod(Object[] arr, int index) {
        return (T) arr[index];
    }

}
