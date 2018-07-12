package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    SimpleStack<Integer> container;

    @Before
    public void prepareForTest() {
        container = new SimpleStack<>();
        container.push(1);
        container.push(2);
        container.push(3);
    }

    @Test
    public void whenPushThenAdded() {
        assertThat(container.get(1), is(2));
    }

    @Test
    public void whenPollThenGetLastAddedElement() {
        assertThat(container.poll(), is(3));
        assertThat(container.size(), is(2));
    }

    @Test
    public void whenIterateThenIterable() {
        Iterator itr = container.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(1));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(3));
        assertThat(itr.hasNext(), is(false));
    }

}