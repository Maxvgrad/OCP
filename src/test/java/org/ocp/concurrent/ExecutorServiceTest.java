package org.ocp.concurrent;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
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


    @Test
    void instanceOfFuture() {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<?> future = service.submit(() -> {});

        assertTrue(future instanceof FutureTask);

    }

    @Test
    void scheduledExecutorService() throws InterruptedException, ExecutionException {
        ExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //scheduledExecutorService.scheduleWithFixedDelay(); ch7q3
        ((ScheduledExecutorService) scheduledExecutorService).scheduleWithFixedDelay(() -> { //Runnable
            System.out.println("Open zoo");
            //return null;
        }, 0, 1, TimeUnit.SECONDS);

        Future<?> result = scheduledExecutorService.submit(() -> System.out.println("Wake stuff"));

        System.out.println(result.get());
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

    //ch7q2
    private class CustomCallable implements Callable<Integer> {

        @Override //throws checked exception
        public Integer call() throws Exception { //no args and return generic type
            return 3;
        }
    }

    //ch7q2
    private class CustomRunnable implements Runnable {

        @Override // does not throw checked exception
        public void run() { //no args

        }
    }

}
