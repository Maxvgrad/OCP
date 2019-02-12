package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExecutorServiceTest {



    @Test
    void isShutDown() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        assertFalse(executorService.isShutdown());
        assertFalse(executorService.isTerminated());
        assertFalse(executorService.awaitTermination(1, TimeUnit.SECONDS));
    }


    @Test
    void isShutdown() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> result = executorService.submit(ThrowableRunnable.wrap(() -> TimeUnit.SECONDS.sleep(10)));

        TimeUnit.SECONDS.sleep(1);

        assertFalse(executorService.isShutdown());
        assertFalse(executorService.isShutdown());

        executorService.shutdown();

        assertTrue(executorService.isShutdown());
        assertFalse(executorService.isTerminated());

        List notSubmittedTasks = executorService.shutdownNow();

        assertTrue(notSubmittedTasks.isEmpty());
        assertTrue(executorService.isShutdown());
        assertFalse(result.isDone());
        assertFalse(result.isCancelled());
        assertFalse(executorService.isTerminated());

    }

    private interface ThrowableRunnable{

        static Runnable wrap(ThrowableRunnable runnable) {
            return () -> {
                try {
                    runnable.run();
                } catch (Exception ex) {
                    throw new IllegalArgumentException(ex);
                }
            };
        }

        void run() throws Exception;
    }


}
