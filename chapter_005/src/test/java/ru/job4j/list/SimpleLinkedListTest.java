package ru.job4j.list;


import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    SimpleLinkedList<String> container;
    Iterator itr;

    @Before
    public void prepareForTest() {
        container = new SimpleLinkedList<>();
        container.add("First");
        container.add("Second");
        container.add("Third");
        itr = container.iterator();
    }

    @Test
    public void whenAddThreeThanAdded() {
        assertThat(container.get(1), is("Second"));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetItemByIndexGreaterThanSizeThenException() {
        container.get(3);
    }

    @Test
    public void whenIterateThanIterable() {
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("First"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("Second"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("Third"));
        assertThat(itr.hasNext(), is(false));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenTryNextOutOfBoundsThenException() {
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThenException() {
        itr.next();
        container.add("Mod");
        itr.next();
    }

}