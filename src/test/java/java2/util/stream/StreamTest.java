package java2.util.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ocp.concurrent.PerformanceCallable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamTest {

    @Test
    void parallelStream() {
        Stream stream = Stream.of(1, 2, 3, 4, 5, 6).parallel();
        stream.forEach(System.out::print);

        assertTrue(stream.isParallel());
    }

    @Test
    void parallelStream2() {
        Stream parallelStream = Arrays.asList(5, 2, 3, 4, 5, 6).parallelStream();

        parallelStream.forEachOrdered(System.out::print);

        assertTrue(parallelStream.isParallel());
    }

    @Test
    void parallelStreamTermilOperationHappensBeforeIntermediate() {
        Stream.of("side", "effect", "note", "text").parallel().map(element -> {
            System.out.println(element); return element.toUpperCase(); }).forEach(System.out::println);
    }

    @Test
    @DisplayName("Use concurrent collections with parallel streams")
    void statefulParallelOperationWithRegularArrayList() {
        List<Integer> list = new ArrayList<>();

        Stream.iterate(1, n -> n << 1).limit(10).parallel().map(n -> { list.add(n); return n; }).count();

        list.forEach(n -> System.out.print(" " + n));
        // null 1 16 8 2 32 256 128 512 64
        // arghhh!
    }


    // Unordered operations
    @Test
    void findAnyOnParallelStream() {
        assertNotEquals(1, IntStream.range(1, 5).parallel().findAny().getAsInt());
    }

    @Test
    void unorderedStream() throws Exception {
        Callable<Long> unorderedTask = new PerformanceCallable(() -> IntStream.range(1, 1000000000).unordered().parallel().sum());
        Callable<Long> orderedTask = new PerformanceCallable(() -> IntStream.range(1, 1000000000).parallel().sum());

        ExecutorService service = Executors.newCachedThreadPool();

        Future<Long> unorderedFuture = service.submit(unorderedTask);
        Future<Long> orderedFuture = service.submit(orderedTask);
        service.shutdown();

        Long unorderedResultTime = unorderedFuture.get(2, TimeUnit.SECONDS);
        Long orderedResultTime = orderedFuture.get(2, TimeUnit.SECONDS);

        System.out.println(orderedResultTime - unorderedResultTime );
        assertTrue(unorderedResultTime < orderedResultTime);
    }



    // Ordered operations
    @Test
    void findFirstSkipLimitOnParallelStream() {
        assertEquals(7, IntStream.range(1, 10).parallel().skip(6).limit(4).findFirst().getAsInt());
    }

    // Fun
    @Test
    void limitMoreThanActualElementsInStream() {
        assertEquals(45L, IntStream.range(1, 10).parallel().limit(100).sum());
    }



}
