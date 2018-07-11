package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {
    SimpleArrayList<Integer> result;

    @Before
    public void create() {
        result = new SimpleArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
    }

    @Test
    public void whenAddThenAdded() {
        result.add(8);
        assertThat(result.get(3), is(8));
    }

    @Test
    public void whenSetThenSets() {
        result.set(1, 10);
        assertThat(result.get(1), is(10));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetOutOfBoundsThenException() {
        result.set(11, 10);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBoundsThenException() {
        result.get(11);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGetNullThanException() {
        result.get(4);
    }

    @Test
    public void whenDeleteExistingThenDelete() {
        result.delete(1);
        assertThat(result.get(1), is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenDeleteOutOfBoundsThenException() {
        result.delete(11);
    }

    @Test
    public void whenUseIteratorThen() {
        Iterator<Integer> itr = result.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(1));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(3));
        assertThat(itr.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemoveThenRemoved() {
        Iterator<Integer> itr = result.iterator();
        result.add(4);
        result.add(5);
        itr.next();
        itr.remove();
        assertThat(result.get(1), is(3));
        result.get(4);
    }


    @Test (expected = NoSuchElementException.class)
    public void whenUseNextOutOFBoundsThenException() {
        Iterator<Integer> itr = result.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifiedWhenIterateThenException() {
        Iterator<Integer> itr = result.iterator();
        itr.next();
        result.delete(0);
        itr.next();

    }

    @Test
    public void whenEnsureCapacityThenSizeDoubled() {
        result = new SimpleArrayList<>(2);
        result.add(1);
        result.add(2);
        result.add(3);
        assertThat(result.size(), is(4));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenEnsureCapacityWhenIterateThenException() {
        result = new SimpleArrayList<>(2);
        result.add(1);
        result.add(2);
        Iterator<Integer> itr = result.iterator();
        assertThat(itr.next(), is(1));
        assertThat(itr.next(), is(2));
        result.add(3);
        itr.next();

    }
}