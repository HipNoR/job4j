package ru.job4j.bbqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking queue for producer consumer.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 08.08.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == 5) {
                System.out.println(String.format("Waiting while queue is full, thread id: %s", Thread.currentThread().getId()));
                this.queue.wait();
            }
            this.queue.add(value);
            System.out.println(String.format("T %s, set value %s", Thread.currentThread().getId(), value));
            this.queue.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == 0) {
                System.out.println(String.format("Waiting while queue is empty, thread id: %s", Thread.currentThread().getId()));
                this.queue.wait();
            }
            T result = this.queue.poll();
            System.out.println(String.format("T %s, value get %s", Thread.currentThread().getId(), result));
            this.queue.notifyAll();
            return result;
        }
    }
}
