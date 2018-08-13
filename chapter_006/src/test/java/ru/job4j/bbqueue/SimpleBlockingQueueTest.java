package ru.job4j.bbqueue;

import org.junit.Test;

public class SimpleBlockingQueueTest {
    final SimpleBlockingQueue<Integer> cont = new SimpleBlockingQueue<>();

    @Test
    public void whenTwoThreadsThen() throws InterruptedException {
        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    cont.offer(1);
                    cont.offer(2);
                    cont.offer(3);
                    cont.offer(4);
                    cont.offer(5);
                    cont.offer(6);
                    cont.offer(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread consumer = new Thread() {
            @Override
            public void run() {
                try {
                    cont.poll();
                    cont.poll();
                    cont.poll();
                    cont.poll();
                    cont.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}