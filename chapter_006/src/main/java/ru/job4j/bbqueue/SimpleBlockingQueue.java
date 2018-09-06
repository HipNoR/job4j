package ru.job4j.bbqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking queue for producer consumer.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 08.08.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int queueSize = 5;

    public SimpleBlockingQueue() {

    }

    public SimpleBlockingQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == queueSize) {
                System.out.println(String.format("%s waiting while queue is full", Thread.currentThread().getName()));
                this.queue.wait();
            }
            this.queue.add(value);
            System.out.println(String.format("%s, set %s", Thread.currentThread().getName(), value));
            this.queue.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == 0) {
                System.out.println(String.format("Waiting while queue is empty, thread id: %s", Thread.currentThread().getName()));
                this.queue.wait();
            }
            T result = this.queue.poll();
            System.out.println(String.format("%s, get %s", Thread.currentThread().getName(), result));
            this.queue.notifyAll();
            return result;
        }
    }
}
