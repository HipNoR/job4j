package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void whenTwoEqualsThenTrue() {
        User first = new User("Roman", "12345");
        User second = new User("Roman", "12345");
        assertThat(first.equals(second), is(true));
    }

    @Test
    public void whenTwoEqualsNameButNotPassportThenFalse() {
        User first = new User("Roman", "123456");
        User second = new User("Roman", "654321");
        assertThat(first.equals(second), is(false));
    }

    @Test
    public void whenOneOfTwoNullThenFalse() {
        User first = new User(null, "12345");
        User second = new User("Roman", "12345");
        assertThat(first.equals(second), is(false));
    }

    @Test
    public void whenThoEqualsThenTrue() {
        User first = new User("Roman", "12345");
        User second = first;
        assertThat(first.equals(second), is(true));
    }
}
