package org.ocp.streams.collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

    @Test
    void counting() {
        assertEquals(Long.valueOf(6), stream.collect(Collectors.counting()));
    }

    @Test
    @DisplayName("https://www.baeldung.com/java-groupingby-collector")
    void groupingBy1Arg() {
        Stream<Integer> stream = Stream.of(1, 3, 4, 5, 6, 6, 8, 10);
        Map<Integer, List<Integer>> map = stream.collect(Collectors.groupingBy(i -> i % 2));
        assertEquals(2, map.size());
        System.out.println(map);
    }

    @Test
    void groupingBy2Arg() {
        Stream<Integer> stream = Stream.of(1, 3, 4, 5, 6, 6, 8, 10);
        Map<Integer, Set<Integer>> map = stream.collect(Collectors.groupingBy(i -> i % 2, Collectors.toSet()));
        assertEquals(2, map.size());
        System.out.println(map);
    }


    @Test
    void groupingBy3Arg() {
        Stream<Integer> stream = Stream.of(1, 3, 4, 5, 6, 6, 8, 10);
        Map<Integer, Set<Integer>> map = stream.collect(Collectors.groupingBy(
                i -> (i % 2), TreeMap::new, Collectors.toSet()));
        assertEquals(2, map.size());
        System.out.println(map);
    }

    @Test
    void groupingByEmpty() {
        Stream<String> stream = Stream.empty();
        Map<Boolean, List<String>> map = stream.collect(Collectors.groupingBy(str -> str.startsWith("c")));

        assertEquals(0, map.size());
        System.out.println(map);
    }

    @Test
    void joining() {
        String uit = Stream.generate(() -> (int)(Math.random()*10)).limit(31).map(i -> "" + i).collect(Collectors.joining());

        assertEquals(31, uit.length());
    }

    @Test
    void partitioningBy() {
        Stream<Integer> stream = Stream.generate(() -> (int)(Math.random()*10));

        Map<Boolean, List<Integer>> map = stream.limit(10).collect(Collectors.partitioningBy(i -> i % 2 == 0));

        assertEquals(2, map.size());
        System.out.println(map);
    }

    @Test
    void partitioningByEmptySet() {
        Stream<Integer> stream = Stream.empty();

        Map<Boolean, List<Integer>> map = stream.limit(10).collect(Collectors.partitioningBy(i -> i % 2 == 0));

        assertEquals(2, map.size());
        System.out.println(map);
    }
}
