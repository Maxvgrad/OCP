package org.ocp.functions;

import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntFunctionTest {

    @Test
    void intFunction() {
        //given
        IntFunction<Integer> intFunction = i -> i + 2;
        //when - then
        assertEquals(Integer.valueOf(4), intFunction.apply(2));

    }
}
