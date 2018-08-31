package ru.job4j.pool;

import org.junit.Test;

public class ThreadPoolTest {

    public class Job implements Runnable {
        int number = 0;

        public Job(int num) {
            this.number = num;
        }
        @Override
        public void run() {
            System.out.println(String.format("Thread %s doing JOB #%s", Thread.currentThread().getName(), number));
        }

        @Override
        public String toString() {
            return String.format("JOB #%s", number);
        }
    }

    @Test
    public void firstTest() throws InterruptedException {
        ThreadPool pool = new ThreadPool();

        for (int index = 0; index < 1000; index++) {
            System.out.println(String.format("Adding job #%s", index));
            pool.work(new Job(index));
        }

       pool.shutdown();
    }

}