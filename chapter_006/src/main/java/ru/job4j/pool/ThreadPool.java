package ru.job4j.pool;

import ru.job4j.bbqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Thread pool class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.6$
 * @since 0.1
 * 22.08.2018
 */
public class ThreadPool {
    private final List<PoolWorker> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(20);

    public ThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < cores; index++) {
            threads.add(new PoolWorker(tasks, index));
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            System.out.println("Unable to add task to queue.");
        }
    }

    public void shutdown() {
        System.out.println("Shutdown");
        for (PoolWorker worker : threads) {
            worker.interrupt();
            System.out.println(String.format("%s is stops.", worker.getName()));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class PoolWorker extends Thread {
        private final SimpleBlockingQueue<Runnable> t;

        public PoolWorker(SimpleBlockingQueue<Runnable> tasks, int index) {
            this.t = tasks;
            this.start();
            System.out.println(String.format("Thread %s is started", index));
        }

        @Override
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    task = t.poll();
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println(String.format("%s is interrupted.", Thread.currentThread().getName()));
                }
            }
        }
    }
}

