package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class CyclicBarrierTest {

    @Test
    void await() throws Exception {
        Track track = new Track();

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        CyclicBarrier barrier = new CyclicBarrier(2);

        IntStream.range(0, 6).forEach(i -> executorService.submit(() -> track.race(barrier)));

        executorService.shutdown();

        TimeUnit.SECONDS.sleep(5);
    }


    private class Track {

        void race(CyclicBarrier barrier) {

            try {

                for (int i = 0; i < 100; i++) {

                    TimeUnit.MILLISECONDS.sleep(300);
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " lap: " + i);
                }

            } catch (Exception ex) {
                throw new IllegalStateException();
            }

        }

    }
}
