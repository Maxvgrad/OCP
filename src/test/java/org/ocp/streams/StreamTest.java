package org.ocp.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.ocp.utils.TestUtils.buildPerson;

class StreamTest {


    //@Test
    void infiniteStreamTest() {
        Stream<Double> infStream = Stream.generate(Math::random);
        assertTimeout(Duration.ofSeconds(2), infStream::count);
    }

    @Test
    void reduceOneArgTest() {
        Optional multiplicationResult = Stream.of(3, 5, 7, 6).reduce(Math::multiplyExact);
        assertEquals(3*5*7*6, multiplicationResult.get());
    }

    @Test
    void reduceOneArgEmptyTest() {
        Optional multiplicationResult = Stream.<Integer>empty().reduce(Math::multiplyExact);
        assertFalse(multiplicationResult.isPresent());
    }

    @Test
    void reduceTwoArgTest() {
        Integer multiplicationResult = Stream.of(3, 5, 7, 6).reduce(20, Math::multiplyExact);
        assertEquals(Integer.valueOf(3*5*7*6*20), multiplicationResult);
    }

    @Test
    void reduceTwoArgEmptyTest() {
        Integer multiplicationResult = Stream.<Integer>empty().reduce(20, Math::multiplyExact);
        assertEquals(Integer.valueOf(20), multiplicationResult);
    }

    @Test
    void reduceThreeArgTest() {
        Integer sumOfPersonId = Stream.of(buildPerson(), buildPerson(), buildPerson(), buildPerson())
                                             .reduce(0, (ident, person) -> ident + person.getId(), Math::addExact);
        assertEquals(Integer.valueOf(10), sumOfPersonId);
    }

    @Test
    void collect3Args() {
        List<Integer> personIdList = Stream.of(buildPerson(), buildPerson(), buildPerson(), buildPerson())
                                           .collect(ArrayList::new, (arr, person) -> arr.add(person.getId()),
                                                    ArrayList::addAll);
        assertEquals(4, personIdList.size());
    }

    @Test
    void collectArgs() {
        List<Integer> personIdList = Stream.of(buildPerson(), buildPerson(), buildPerson(), buildPerson()).map(
                PersonDto::getId).sorted().collect(Collectors.toCollection(ArrayList::new));
        assertEquals(Integer.valueOf(1), personIdList.get(0));
    }

    @Test
    void distinctTest() {
        List<Integer> uniqNumbers = Stream.of(1, 1, 1, 1, 1).distinct().collect(Collectors.toList());
        assertEquals(1, uniqNumbers.size());
    }

    @Test
    void skipTest() {
        Integer sum = Stream.of(1, 1, 1, 1, 2).skip(4).reduce(0, Integer::sum);
        assertEquals(Integer.valueOf(2), sum);
    }

    @Test
    void limitTest() {
        Integer sum = Stream.of(1, 1, 1, 1, 2).limit(2).reduce(0, Integer::sum);
        assertEquals(Integer.valueOf(2), sum);
    }

    @Test
    void skipLimitTest() {
        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
        s.skip(5).limit(2).reduce(Integer::sum).ifPresent((sum) -> assertEquals(Integer.valueOf(13), sum));
    }

    @Test
    void flatMapTest() {
        List<Integer> ints = Arrays.asList(1, 3, 5, 7);
        List<Double> doubles = Arrays.asList(2.0, 4.0, 6.0, 8.0);
        List<? extends Number> numbers = Stream.of(ints, doubles).flatMap(List::stream).collect(Collectors.toList());
        assertEquals(8, numbers.size());
    }

    @Test
    @DisplayName("Debug")
    void debug1Test() {
        Stream.generate(() -> "Elsa")
              .filter(n -> n.length() == 4)
              .limit(2)
              .sorted()
              .forEach(System.out::println);
    }

    @Test
    @DisplayName("Debug")
    void debug2Test() {
        Stream.iterate(1, (n) -> n+1)
              .limit(5)
              .peek(System.out::print)
              .filter(n -> n%2==1)
              .forEach(System.out::print);
        System.out.println();
        Stream.iterate(1, (n) -> n+1)
              .filter(n -> n%2==1)
              .peek(System.out::print)
              .limit(5)
              .forEach(System.out::print);
    }
}
