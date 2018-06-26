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
}
