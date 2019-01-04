package org.ocp.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectionsTest {


    @Test
    @Tag("ch3")
    @DisplayName("Comparator.reverseOrder()")
    void binarySearchInReverseOrderTest() {
        List<Integer> list = Arrays.asList(5, 4, 7, 1);
        Collections.sort(list, Comparator.reverseOrder());
        assertEquals(-1, Collections.binarySearch(list, 1));
        assertEquals(3, Collections.binarySearch(list, 1, Comparator.reverseOrder()));
    }
}
