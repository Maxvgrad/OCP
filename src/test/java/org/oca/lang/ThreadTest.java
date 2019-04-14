package org.oca.lang;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadTest {

    private final StringBuilder accumulator = new StringBuilder();


    @Test
    void newState() {
        Thread thread = new Thread();

        assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    void runnableState() {
        Thread thread = new Thread(() -> System.out.println("Hey"));

        thread.run();

        assertEquals(Thread.State.RUNNABLE, thread.getState());
    }


    @Test
    void priority() throws Exception {

        Runnable task = () -> accumulator.append(Thread.currentThread().getName());

        Thread t1 = buildThread("t1", Thread.MIN_PRIORITY, task);
        Thread t5 = buildThread("t5", Thread.NORM_PRIORITY, task);
        Thread t10 = buildThread("t10", Thread.MAX_PRIORITY, task);

        t1.start();
        t5.start();
        t10.start();

        TimeUnit.SECONDS.sleep(1);

        assertEquals("t10t5t1", accumulator.toString());
    }

    private Thread buildThread(String name, int priority, Runnable task) {
        Thread thread = new Thread(task);
        thread.setPriority(priority);
        thread.setName(name);
        return thread;
    }

    @Test
    void myThreadTest() {
        new MyThread().start();
    }

    class MyThread extends Thread {
        int i = 0;

        public void run() {
            while (true) {
                if (i % 2 == 0) {
                    System.out.println("Hello World");
                }
            }
        }
    }
}
