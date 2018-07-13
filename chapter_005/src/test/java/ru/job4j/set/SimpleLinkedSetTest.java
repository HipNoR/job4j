package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedSetTest {

    SimpleLinkedSet<String> container;

    @Before
    public void prepareForTests() {
        container = new SimpleLinkedSet<>();
        container.add("First");
        container.add("Second");
        container.add("Third");
    }

    @Test
    public void whenAddThreeDifferentThenSizeThree() {
        assertThat(container.size(), is(3));
    }

    @Test
    public void whenAddThreeDifferentAndOneCloneThenSizeThree() {
        container.add("First");
        assertThat(container.size(), is(3));
    }

    @Test
    public void whenIterateThenIterable() {
        Iterator itr = container.iterator();
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("First"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("Second"));
        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is("Third"));
        assertThat(itr.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThenException() {
        container = new SimpleLinkedSet<>();
        container.add("First");
        Iterator itr = container.iterator();
        assertThat(itr.next(), is("First"));
        container.add("Third");
        itr.next();
    }
}