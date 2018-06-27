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
    public void whenSubtractsFromAccount() {
        Account account = new Account(100, "12345");
        account.subAmount(50);
        assertThat(account.getValue(), is(50.0));
    }

    @Test
    public void whenAdditionToAccount() {
        Account account = new Account(100, "12345");
        account.addAmount(50);
        assertThat(account.getValue(), is(150.0));
    }

    @Test
    public void whenGetReq() {
        Account account = new Account(100, "12345");
        assertThat(account.getRequisites(), is("12345"));
    }
}
