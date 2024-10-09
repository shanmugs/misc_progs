package com.rresino.concurrent.java8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created by rresino on 18/01/17.
 */
class TaskManagerTest {

    @Test
    public void RunTestTaskManagerWithFor() {
        System.out.println("RunTestTaskManagerWithFor: Start");
        TaskManager manager = new TaskManager();

        addTasks(manager, 10);

        try {
            manager.waitUtilAllComplete(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RunTestTaskManagerWithFor: End");
    }

    @Test
    public void RunTestTaskManagerWithCompletableFuture() {
        System.out.println("RunTestTaskManagerWithFor: Start");

        TaskManager manager = new TaskManager();

        CompletableFuture.allOf(CompletableFuture.runAsync(()->{
                    addTasks(manager, 5);
                }),
                CompletableFuture.runAsync(()->{
                    addTasks(manager, 5);
                }));
        try {
            manager.waitUtilAllComplete(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RunTestTaskManagerWithCompletableFuture: End");
    }

    private void addTasks(TaskManager manager, int tasksItems) {
        for (int i = 0; i < tasksItems; i++) {
            manager.executeTask(Suppliers.runnable.get());
        }
    }

}