package com.rresino.concurrent.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rresino on 17/01/17.
 */
public class TaskManager {

    AtomicInteger tasksStarted = new AtomicInteger(0);
    AtomicInteger tasksEnded = new AtomicInteger(0);

    void executeTask(final Runnable task) {
        tasksStarted.incrementAndGet();
        CompletableFuture.runAsync(task).thenRun(()-> tasksEnded.incrementAndGet());
    }

    boolean allTaskCompleted() {
        return tasksStarted.intValue()==tasksEnded.intValue();
    }

    void waitUtilAllComplete(int refreshTime) throws InterruptedException {
        while(!this.allTaskCompleted()) {
            Thread.sleep(1000);
        }
    }

}
