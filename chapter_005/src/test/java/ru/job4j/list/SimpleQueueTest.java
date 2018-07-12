package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<String> container;

    @Before
    public void prepareForTest() {
        container = new SimpleQueue<>();
        container.push("First");
        container.push("Second");
        container.push("Third");
    }

    @Test
    public void whenPollThenGetsFirstAddedElement() {
        assertThat(container.size(), is(3));
        String result = container.poll();
        assertThat(result, is("First"));
        assertThat(container.size(), is(2));
    }

    @Test
    public void poll() {
    }

}