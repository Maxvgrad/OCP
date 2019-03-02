package org.ocp.concurrent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ScheduledExecutorServiceTest {

    private ScheduledExecutorService scheduledExecutorService;

    @BeforeEach
    void setUp() {
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    @AfterEach
    void tearDown() {
        scheduledExecutorService.shutdown();
    }

    @Test
    void submit() throws Exception {

        scheduledExecutorService.submit(() -> System.out.println("submit()"));
        TimeUnit.SECONDS.sleep(5);

    }

    @Test
    void execute() throws Exception {
        scheduledExecutorService.execute(() -> System.out.println("execute()"));
        TimeUnit.SECONDS.sleep(5);

    }
}
