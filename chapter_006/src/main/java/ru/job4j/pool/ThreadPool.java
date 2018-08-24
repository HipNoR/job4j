package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread pool class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 22.08.2018
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private final int size;
    private volatile boolean isRunning = true;

    public ThreadPool() throws InterruptedException {
        size = Runtime.getRuntime().availableProcessors();
        System.out.println("ThreadPool size is: " + size);
        for (int i = 0; i < size; i++) {
            threads.add(new PoolWorker());
            threads.get(i).start();
        }
    }

    public void work(Runnable job) {
        synchronized (tasks) {
            if (isRunning) {
                tasks.offer(job);
                tasks.notifyAll();
            }
        }
    }

    public void shutdown() {
        System.out.println("shutdown");
        isRunning = false;
    }

    private final class PoolWorker extends Thread {

        @Override
        public void run() {
            System.out.println(String.format("Thread %s is started", Thread.currentThread().getId()));
            while (isRunning) {
                Runnable task;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            System.out.println(String.format("Waiting mode in thread %s", Thread.currentThread().getId()));
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(String.format("Thread %s getting the JOB", Thread.currentThread().getId()));
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }
}
