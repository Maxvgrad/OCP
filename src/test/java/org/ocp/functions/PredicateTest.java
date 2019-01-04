package org.ocp.functions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PredicateTest {

    private Predicate<Integer> evenNum = num -> num % 2 == 0;
    private Predicate<Integer> greaterThenEight = num -> num > 8;


    @Test
    @Tag("ch4")
    void andTest() {
        List<Integer> result = Arrays.asList(1, 10, 5, 3).stream().filter(evenNum.and(greaterThenEight))
                                                     .collect(Collectors.toList());

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).intValue());
    }

    @Test
    @Tag("ch4")
    void notTest() {
        List<Integer> result = Arrays.asList(1, 10, 5, 3).stream()
                                                     .filter(Predicate.not(evenNum))
                                                     .collect(Collectors.toList());

        assertEquals(3, result.size());
    }

    @Test
    @Tag("ch4")
    void orTest() {
        List<Integer> result = Arrays.asList(1, 10, 5, 2).stream()
                                                     .filter(evenNum.or(greaterThenEight))
                                                     .collect(Collectors.toList());

        assertEquals(2, result.size());
    }


}
