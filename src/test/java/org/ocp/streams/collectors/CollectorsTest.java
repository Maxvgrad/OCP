package org.ocp.streams.collectors;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectorsTest {

    private Stream<Integer> stream = Stream.of(1, 3, 4, 5, 6, 6);

    @Test
    void averageDouble() {
        Double average = stream.collect(Collectors.averagingDouble(Double::valueOf));
        assertEquals(Double.valueOf(4.166666666666667), average);
    }

    @Test
    void averageInteger() {
        Double average = stream.collect(Collectors.averagingInt(i -> i));
        assertEquals(Integer.valueOf(4), average);
    }

    @Test
    void averageLong() {
        Double average = stream.mapToLong(i -> i).boxed().collect(Collectors.averagingLong(l -> l));
        assertEquals(Double.valueOf(4), average);
    }

}
