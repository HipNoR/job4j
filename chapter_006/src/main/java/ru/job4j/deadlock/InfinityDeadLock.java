package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * DeadLock guaranteed class.
 * Deliberately omitted the method countDown() to enter the program to the deadlock.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 13.09.2018
 */
public class InfinityDeadLock {

    public InfinityDeadLock() {
        CountDownLatch count = new CountDownLatch(5);

        new Thread(new MyTask(count)).start();

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The End");
    }


    private class MyTask implements Runnable {
        CountDownLatch lock;

        public MyTask(CountDownLatch lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            for (int index = 5; index != 0; index--) {
                System.out.println(index);
                lock.countDown();
            }
        }
    }
}
