package org.ocp.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.ocp.testutils.TestUtils.buildPerson;

class StreamTest {


    //@Test
    void infiniteStreamTest() {
        Stream<Double> infStream = Stream.generate(Math::random);
        assertTimeout(Duration.ofSeconds(2), infStream::count);
    }

    @Test
    void reduceOneArgTest() {
        Optional multiplicationResult = Stream.of(3, 5, 7, 6).reduce(Math::multiplyExact);
        assertEquals(3 * 5 * 7 * 6, multiplicationResult.get());
    }

    @Test
    void reduceOneArgEmptyTest() {
        Optional multiplicationResult = Stream.<Integer>empty().reduce(Math::multiplyExact);
        assertFalse(multiplicationResult.isPresent());
    }

    @Test
    void reduceTwoArgTest() {
        Integer multiplicationResult = Stream.of(3, 5, 7, 6).reduce(20, Math::multiplyExact);
        assertEquals(Integer.valueOf(3 * 5 * 7 * 6 * 20), multiplicationResult);
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
        assertEquals(Integer.valueOf(42), sumOfPersonId);
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
        assertEquals(Integer.valueOf(5), personIdList.get(0));
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
        Stream.iterate(1, (n) -> n + 1)
              .limit(5)
              .peek(System.out::print)
              .filter(n -> n % 2 == 1)
              .forEach(System.out::print);
        System.out.println();
        Stream.iterate(1, (n) -> n + 1)
              .filter(n -> n % 2 == 1)
              .peek(System.out::print)
              .limit(5)
              .forEach(System.out::print);
    }

    @Test
    @Tag("ch4")
    void averageTest() {
        OptionalDouble averageOpt = Stream.of(4, 4, 5, 3).mapToInt(x -> x).average();
        assertEquals(4.0, averageOpt.orElse(-1));
    }

    @Test
    @Tag("ch4")
    void ofDoubleStream() {
        DoubleStream doubleStream = DoubleStream.of(3, 4.0);
        assertEquals(2, doubleStream.count());
    }

    @Test
    @Tag("ch4")
    void divisionTest() {
        DoubleStream.iterate(1, n -> n / 2).limit(10).forEach(System.out::println);
    }

    @Test
    void randomClassTest() {
        assertEquals(IntStream.empty().getClass(), new Random().ints().getClass());
    }

    @Test
    void rangeAndClosedRangeTest() {
        assertEquals(10, IntStream.range(0, 10).count());
        assertEquals(11, IntStream.rangeClosed(0, 10).count());
        assertEquals(IntStream.rangeClosed(0, 10).getClass(), IntStream.empty().getClass());
    }

    @Test
    @Tag("ch4")
    void intSummaryStatistics() {
        IntSummaryStatistics statistics = IntStream.of(1, 3, 4, 5, 8).summaryStatistics();
        assertEquals(8, statistics.getMax());
        assertEquals(1, statistics.getMin());
        assertEquals(21, statistics.getSum());
        assertEquals(4.2, statistics.getAverage());
        assertEquals(5, statistics.getCount());
    }

    @Test
    @Tag("ch4")
    void longSummaryStatistics() {
        LongSummaryStatistics statistics = LongStream.of(1, 3, 4, 5, 8).summaryStatistics();
        assertEquals(8, statistics.getMax());
        assertEquals(1, statistics.getMin());
        assertEquals(21, statistics.getSum());
        assertEquals(4.2, statistics.getAverage());
        assertEquals(5, statistics.getCount());
    }

    @Test
    @Tag("ch4")
    void doubleSummaryStatistics() {
        DoubleSummaryStatistics statistics = DoubleStream.of(1.0, 3D, 4D, 5.0, 8).summaryStatistics();
        assertEquals(8, statistics.getMax());
        assertEquals(1, statistics.getMin());
        assertEquals(21, statistics.getSum());
        assertEquals(4.2, statistics.getAverage());
        assertEquals(5, statistics.getCount());
    }

    @Test
    @Tag("ch4")
    void listTest() {
        List<String> cats = new ArrayList<>();
        cats.add("Joe");
        cats.add("Jon");
        Stream<String> catsStram = cats.stream();
        cats.add("Moe");
        assertEquals(3, catsStram.count());
    }

    @Test
    void sort() {
        List<Item> l = Arrays.asList(new Item(1, "Screw"),
                                     new Item(2, "Nail"),
                                     new Item(3, "Bolt"));

        assertThrows(ClassCastException.class, () -> l.stream().sorted().forEach(System.out::println));

        l.stream().map(i -> i.name).sorted().forEach(System.out::println);
        l.stream().sorted((i1, i2) -> i1.name.compareTo(i2.name)).forEach(System.out::println);
        l.stream().sorted(Comparator.comparing(i1 -> i1.name)).forEach(System.out::println);

    }

    @Test
    void reduce() {

        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);
        assertEquals(Integer.valueOf(9), ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b));
        //assertEquals(Integer.valueOf(9), ls.stream().max(Integer::max).get());
        assertEquals(Integer.valueOf(9), ls.stream().max(Integer::compare).get());
        //assertEquals(Integer.valueOf(9), ls.stream().max((a, b)->a>b?a:b).get());

    }

    @Test
    void counting() {
        List<String> names = Arrays.asList("greg", "dave", "don", "ed", "fred");
        Map<Integer, Long> data = names.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(data.values());
    }

    @Test
    void parallelStreamTest() {
        List<String> vals = Arrays
                .asList("a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e",
                        "f", "g", "a", "b", "c", "d", "e", "f", "g");

        String join = vals.parallelStream().peek(System.out::println)
                          .reduce("_", (a, b) -> {
                              System.out.println(
                                      "reducing " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                              return a.concat(b);
                          }, (a, b) -> {
                              System.out.println(
                                      "combining " + a + " and " + b + " Thread: " + Thread.currentThread().getName());
                              return a.concat(b);
                          });
        System.out.println(join);
    }

    private class Item {
        String name;
        Integer num;

        public Item(Integer num, String name) {
            this.name = name;
            this.num = num;
        }
    }
}
