package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    @Test
    public void whenTwoEqualsThenTrue() {
        Account first = new Account(140, "123456");
        Account second = new Account(140, "123456");
        assertThat(first.equals(second), is(true));
    }

    @Test
    public void whenFirstSendToSecond() {
        Account first = new Account(140, "123456");
        Account second = new Account(100, "654321");
        first.transfer(first, second, 40.0);
        assertThat(second.getValue(), is(140.0));
    }

    @Test
    public void whenFirstSendToSecondMoreThenHaveThenFalse() {
        Account first = new Account(140, "123456");
        Account second = new Account(100, "654321");
        assertThat(first.transfer(first, second, 200.0), is(false));
    }
}
