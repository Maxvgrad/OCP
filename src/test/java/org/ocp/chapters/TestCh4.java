package org.ocp.chapters;

import org.junit.jupiter.api.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCh4 {

    @Test
    void q1Test() {
        Stream<String> stream = Stream.iterate("", s -> s + "1");
        System.out.println(stream.limit(2).map(x -> x + "2"));
    }

    @Test
    void qTest() {
        Stream<String> stream = Stream.iterate("", s -> s + "1");
        System.out.println(stream.limit(2).map(x -> x + "2").collect(Collectors.toList()));
    }

    @Test
    void q3Test() {
        Predicate<? super String> predicate = s -> s.length() > 3;
        Stream<String> stream = Stream.iterate("-", s -> s + s);
        boolean b1 = stream.noneMatch(predicate);
        boolean b2 = stream.anyMatch(predicate);
        System.out.println(b1 + " " + b2);
    }

    @Test
    void q5Test() {
    }

    @Test
    void q6Test() {
        Stream<String> s = Stream.generate(() -> "meow");
        boolean math = s.allMatch(String::isEmpty);
        assertFalse(math);
    }

    @Test
    void q8Test() {
        assertEquals(OptionalDouble.class, IntStream.empty().average().getClass());
        assertEquals(OptionalInt.class, IntStream.empty().findFirst().getClass());
        assertEquals(0, IntStream.empty().sum());
    }

    @Test
    void q9Test() {
        LongStream ls = LongStream.of(1, 2, 3);
        OptionalLong opt = ls.map(n -> n * 10).filter(n -> n < 5).findFirst();
        if (opt.isPresent()) {
            assertTrue(false);
            System.out.println(opt.getAsLong());
        }
        opt.ifPresent(s -> assertTrue(false));
    }

    @Test
    void q11Test() {
        Stream.iterate(1, x -> x++).limit(5).map(x -> x + "").collect(Collectors.joining(""));
    }
}
