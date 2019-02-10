package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * DeadLock guaranteed class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 13.09.2018
 */
public class Locker extends Thread {

    private final CountDownLatch latch;
    private final Object obj1;
    private final Object obj2;

    public Locker(CountDownLatch latch, final Object obj1, final Object obj2) {
        this.latch = latch;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            latch.countDown();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj2) {
            System.out.println("Finish");
        }
    }

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        CountDownLatch latch = new CountDownLatch(2);

        new Locker(latch, obj1, obj2).start();
        new Locker(latch, obj2, obj1).start();
    }
}
