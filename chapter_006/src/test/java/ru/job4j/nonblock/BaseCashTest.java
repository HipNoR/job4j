package ru.job4j.nonblock;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseCashTest {
    BaseCash base = new BaseCash();


    @Test
    public void firstTest() throws InterruptedException {
        Thread one = new Thread() {
            @Override
            public void run() {
                base.add(new Base(1, "one"));
                base.add(new Base(2, "two"));
                base.add(new Base(3, "three"));
                base.add(new Base(4, "four"));
                base.update(new Base(3, "threeOne"));
                base.update(new Base(3, "threeTwo"));
                base.update(new Base(3, "threePlus"));
                base.update(new Base(3, "threeOPS"));
                base.add(new Base(5, "five"));

            }
        };

        Thread two = new Thread() {
            @Override
            public void run() {
                base.add(new Base(10, "ten"));
                base.add(new Base(20, "twenty"));
                base.add(new Base(30, "thirty"));
                base.add(new Base(40, "forty"));
                base.update(new Base(3, "threeTen"));
                base.update(new Base(3, "threeTwenty"));
                base.update(new Base(3, "threeThirty"));
                base.add(new Base(50, "fifty"));
            }
        };

       Thread three = new Thread() {
            @Override
            public void run() {
               base.get(1);
               base.get(2);
               base.get(3);
            }
        };

       one.start();
       two.start();
       three.start();
       one.join();
       two.join();
       three.join();

    }

    @Test
    public void secondTest() throws InterruptedException {
        base.add(new Base(1, "one"));

        Thread one = new Thread() {
            @Override
            public void run() {
                base.update(new Base(1, "100One"));
                base.update(new Base(1, "200One"));
                base.update(new Base(1, "300One"));
                base.update(new Base(1, "400One"));

            }
        };

        Thread two = new Thread() {
            @Override
            public void run() {
                base.update(new Base(1, "1One"));
                base.update(new Base(1, "2One"));
                base.update(new Base(1, "3One"));
                base.update(new Base(1, "4One"));
            }
        };

        Thread three = new Thread() {
            @Override
            public void run() {
                base.update(new Base(1, "10One"));
                base.update(new Base(1, "20One"));
                base.update(new Base(1, "30One"));
                base.update(new Base(1, "40One"));
            }
        };

        one.start();
        two.start();
        three.start();
        one.join();
        two.join();
        three.join();

    }
}