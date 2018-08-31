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
    private final int cores;
    private volatile boolean isAlive = true;

    public ThreadPool() throws InterruptedException {
        cores = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < cores; index++) {
            threads.add(new PoolWorker(tasks, index));
        }
    }

    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.offer(job);
            tasks.notifyAll();
        }
    }

    public synchronized void shutdown() {
        isAlive = false;
        System.out.println("shutdown");
    }

    private class PoolWorker extends Thread {
        Queue<Runnable> t;

        public PoolWorker(Queue<Runnable> tasks, int index) {
            this.t = tasks;
            new Thread(this, "Thread #" + index).start();
            System.out.println(String.format("Thread %s is started", index));
        }

        @Override
        public void run() {
            Runnable task;
            while (isAlive) {
                synchronized (t) {
                    while (t.isEmpty()) {
                        try {
                            System.out.println(String.format("Thread %s is waiting for JOB", Thread.currentThread().getName()));
                            t.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = t.poll();
                }
                task.run();
            }
        }
    }
}
