package sun.tracing.dtrace;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * Simply put, there is no guarantee which thread will run when and for how much time.
 * In some OSs like Windows, CPU time is given to threads in ratio of their priority.
 * While in other OSs like Unix, a lower priority thread executes only after higher priority thread ends.
 */
class JavaTest {

    private CyclicBarrier cyclicBarrier;

    @Test
    void test() throws Exception {
        cyclicBarrier = new CyclicBarrier(3);
        Thread highPriorityThread = createThread(Thread.MAX_PRIORITY, cyclicBarrier);
        Thread lowPriorityThread = createThread(Thread.MIN_PRIORITY, cyclicBarrier);

        highPriorityThread.start();
        lowPriorityThread.start();

        cyclicBarrier.await();

        System.out.println("End");
    }

    private Thread createThread(int priority, CyclicBarrier cyclicBarrier) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 1000; i++) {
                    System.out.println("p - " + getPriority());
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
        };

        thread.setPriority(priority);
        return thread;
    }
}
