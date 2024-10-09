package com.rresino.concurrent.java8;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Created by rresino on 18/01/17.
 */
public class Suppliers {

    static final Random rd = new Random(new Date().getTime());

    private Suppliers() {
    }

    public static Supplier<Runnable> runnable = (() -> new Runnable() {
        @Override
        public void run() {
            String name = "task_" + rd.nextInt();
            System.out.println("Task " + name + " begin.");

            try {
                Thread.sleep(rd.nextInt(10) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task " + name + " ended.");
        }
    });
}
