package org.ocp.concurrent;

import java.util.concurrent.Callable;

public class PerformanceCallable implements Callable<Long> {

    private final Runnable task;

    public PerformanceCallable(Runnable task) {
        this.task = task;
    }

    @Override
    public Long call() throws Exception {
        long t1 = System.nanoTime();

        task.run();

        return System.nanoTime() - t1;
    }
}
