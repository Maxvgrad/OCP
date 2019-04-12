package org.ocp.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This Comparator returns the difference in length of two strings.
 * Thus, two strings of same length are considered equal by this comparator.
 * Arrays.binarySearch() method returns the index of the search key,
 * if it is contained in the list; otherwise, (-(insertion point) - 1).
 * The insertion point is defined as the point at which the key would be inserted into the list:
 * the index of the first element greater than the key, or list.size(),
 * if all elements in the list are less than the specified key.
 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
 */
class ArraysTest {

    private String[] array;

    @Test
    void sortCapitalLetterFirst() {
        array = new String[]{"max", "Max"};
        assertEquals("max", array[0]);
        Arrays.sort(array);
        assertEquals("Max", array[0]);
    }

    @Test
    void sortNumber() {
        array = new String[]{"3-max", "1-max", "2-max"};
        assertEquals("3-max", array[0]);
        Arrays.sort(array);
        assertEquals("1-max", array[0]);
    }

    @Test
    void sortComparatorAsc() {
        array = new String[]{"3-max", "1-max", "2-max"};
        Arrays.sort(array, Comparator.comparing(Function.identity()));
        assertEquals("1-max", array[0]);
    }

    @Test
    void sortComparatorDesc() {
        array = new String[]{"3-max", "1-max", "2-max"};
        Arrays.sort(array, (s1, s2) -> s2.hashCode() - s1.hashCode());
        assertEquals("3-max", array[0]);
    }

    @Test
    void sortByLengthIfPrefixTheSame() {
        array = new String[]{"maxVinogradov", "max"};
        Arrays.sort(array);
        assertEquals("max", array[0]);
    }

    @Test
    void binarySearch() {
        String[] sa = { "a", "aa", "aaa", "aaaa" };
        String search = "a";
        assertEquals(0, Arrays.binarySearch(sa, search));

        search = "0";
        assertEquals(-1, Arrays.binarySearch(sa, search));

        search = "b";
        assertEquals(-5, Arrays.binarySearch(sa, search));

        search = "a1";
        assertEquals(-2, Arrays.binarySearch(sa, search));

        search = "aa1";
        assertEquals(-3, Arrays.binarySearch(sa, search));
    }
}
