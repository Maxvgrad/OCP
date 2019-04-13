package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void test() throws Exception {

        Merger m = new Merger();

        CyclicBarrier cb = new CyclicBarrier(2, m);
        ItemProcessor ip = new ItemProcessor(cb);
        ip.start();

        TimeUnit.SECONDS.sleep(2);
        assertEquals(1, cb.getNumberWaiting());
        assertEquals(2, cb.getParties());

        cb.await();
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

    private class ItemProcessor extends Thread {
        CyclicBarrier cb;

        public ItemProcessor(CyclicBarrier cb) {
            this.cb = cb;
        }

        public void run() {
            System.out.println("processed");
            try {
                cb.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    private class Merger implements Runnable {
        public void run() {
            System.out.println("Value Merged");
        }
    }
}
