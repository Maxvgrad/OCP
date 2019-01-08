package org.ocp.streams;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Optional;
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
}
