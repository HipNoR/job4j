package ru.job4j.nonblock;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BaseCashTest {
    BaseCash base = new BaseCash();

    @Test
    public void whenThreeThreadsThenWorks() throws InterruptedException {
        Thread one = new Thread() {
            @Override
            public void run() {
                base.add(new Base(1, "one"));
                base.add(new Base(2, "two"));
            }
        };

        Thread two = new Thread() {
            @Override
            public void run() {
                base.add(new Base(3, "three"));
                base.add(new Base(4, "four"));
                Base one = base.get(1);
                try {
                    sleep(1000);
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
                one.setName("Modified One");
                base.update(one);
            }
        };

        Thread three = new Thread() {
            @Override
            public void run() {
                base.add(new Base(5, "five"));
                base.add(new Base(6, "six"));
                Base one = base.get(1);
                one.setName("Modified One");
                base.update(one);
                Base two = base.get(1);
                two.setName("Modified One-two");
                base.update(two);
                Base three = base.get(1);
                three.setName("Modified One-two-three");
                base.update(three);
            }
        };

        one.start();
        two.start();
        three.start();
        one.join();
        two.join();
        three.join();
    }

    @Test (expected = OptimisticException.class)
    public void exceptionTest() {
        BaseCash cash = new BaseCash();
        cash.add(new Base(1, "one"));
        Base tempOne = cash.get(1);
        Base modifier = new Base(1, "newOne");
        tempOne.setName("modifiedOne");
        cash.update(modifier);
        cash.update(tempOne);
    }

    @Test
    public void whenDeleteThenDeleted() {
        BaseCash cash = new BaseCash();
        cash.add(new Base(1, "one"));
        cash.add(new Base(2, "two"));
        assertThat(cash.get(1).getName(), is("one"));
        cash.delete(new Base(1, "one"));
        assertNull(cash.get(1));
    }

    @Test
    public void whenAddThenSizeIncreased() {
        BaseCash cash = new BaseCash();
        cash.add(new Base(1, "one"));
        cash.add(new Base(2, "two"));
        assertThat(cash.size(), is(2));
    }
}