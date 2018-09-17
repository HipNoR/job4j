package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * DeadLock guaranteed class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 13.09.2018
 */
public class InfinityDeadLock {

    public InfinityDeadLock() {
        CountDownLatch latchA = new CountDownLatch(5);
        CountDownLatch latchB = new CountDownLatch(5);

        new Thread(new TaskA(latchA, latchB)).start();
        new Thread(new TaskB(latchA, latchB)).start();

        try {
            latchA.await();
            latchB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The End");
    }


    private class TaskA implements Runnable {
        CountDownLatch latchA;
        CountDownLatch latchB;

        public TaskA(CountDownLatch latchA, CountDownLatch latchB) {
            this.latchA = latchA;
            this.latchB = latchB;
        }
        @Override
        public void run() {
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int index = 5; index != 0; index--) {
                System.out.println("TaskA count" + index);
                latchA.countDown();
            }
        }
    }

    private class TaskB implements Runnable {
        CountDownLatch latchA;
        CountDownLatch latchB;

        public TaskB(CountDownLatch latchA, CountDownLatch latchB) {
            this.latchA = latchA;
            this.latchB = latchB;
        }
        @Override
        public void run() {
            try {
                latchA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int index = 5; index != 0; index--) {
                System.out.println("TaskB count" + index);
                latchB.countDown();
            }
        }
    }
}
