package org.ocp.functions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class ConsumerTest {

    private Consumer<String> operationConcater = str -> System.out.println(str.concat("+"));
    private Consumer<String> numberConcater = str -> System.out.println(str.concat(str));

    @Test
    void consumerAndThenTest() {
        Arrays.asList("one", "two", "five", "seven").forEach(operationConcater.andThen(numberConcater));


    }
}
