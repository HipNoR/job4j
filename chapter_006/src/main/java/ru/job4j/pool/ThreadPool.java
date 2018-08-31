package ru.job4j.pool;

import ru.job4j.bbqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Thread pool class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 22.08.2018
 */
public class ThreadPool {
    private final List<PoolWorker> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);
    private volatile boolean isAlive = true;

    public ThreadPool() throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < cores; index++) {
            threads.add(new PoolWorker(tasks, index));
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (!this.isAlive) {
            throw new IllegalStateException("ThreadPool is stopped");
        }
        tasks.offer(job);
    }

    public synchronized void shutdown() {
        System.out.println("shutdown");
        isAlive = false;
        for (PoolWorker worker : threads) {
            worker.workerStop();
            System.out.println(String.format("%s is stopped.", worker.getName()));
        }
    }

    private class PoolWorker extends Thread {
        private SimpleBlockingQueue<Runnable> t;
        private boolean isAlive = true;

        public PoolWorker(SimpleBlockingQueue<Runnable> tasks, int index) throws InterruptedException {
            this.t = tasks;
            new Thread(this, "Thread #" + index).start();
            System.out.println(String.format("Thread %s is started", index));
        }

        @Override
        public void run() {
            Runnable task;
            while (isAlive) {
                try {
                    task = t.poll();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void workerStop() {
            isAlive = false;
            this.interrupt();
        }
    }
}
