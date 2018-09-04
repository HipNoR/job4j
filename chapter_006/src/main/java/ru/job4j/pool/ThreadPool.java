package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread pool class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.5$
 * @since 0.1
 * 22.08.2018
 */
public class ThreadPool {
    private final List<PoolWorker> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>(10);
    private boolean isStopped = false;

    public ThreadPool() throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < cores; index++) {
            threads.add(new PoolWorker(tasks, index));
        }
        // Стоит ли применять такую конструкцию? или достаточно в конструкторе воркера this.join?
//        for (PoolWorker worker : threads) {
//            try {
//                worker.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void work(Runnable job) throws InterruptedException {
        synchronized (tasks) {
            if (isStopped) {
                throw new InterruptedException("Pool is stopped");
            }
            tasks.offer(job);
            tasks.notifyAll();
            Thread.sleep(10); // притормозить main и позволить выполниться потокам исполнителям
        }
    }

    public synchronized void shutdown() {
        System.out.println("shutdown");
        isStopped = true;
        for (PoolWorker worker : threads) {
            worker.interrupt();
            System.out.println(String.format("%s is stopped.", worker.getName()));
        }
        try {
            Thread.sleep(10); //Останавливаем main, что бы другие потоки завершили работу.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class PoolWorker extends Thread {
        private final Queue<Runnable> t;

        public PoolWorker(Queue<Runnable> tasks, int index) throws InterruptedException {
            this.t = tasks;
            Thread worker = new Thread(this, "Thread #" + index);
            worker.start();
            System.out.println(String.format("Thread %s is started", index));
            this.join();
        }

        @Override
        public void run() {
            synchronized (t) {
                while (!Thread.currentThread().isInterrupted()) {
                    Runnable task = t.poll();
                    if (task == null) {
                        try {
                            t.wait();
                            t.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        task.run();
                    }
                }
            }
        }
    }
}
