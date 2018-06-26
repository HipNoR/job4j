package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenAddTwoUsers1() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        System.out.println(bank);
        //assertThat(bank.getUserName("54321"), is("Ivan"));
    }

    @Test
    public void whenAddTwoUsers() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        assertThat(bank.getUserName("54321"), is("Ivan"));
    }

    @Test
    public void whenAddThreeUsersThenDeleteOne() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addUser(new User("Oleg", "55555"));
        bank.deleteUser(bank.getUser("54321"));
        assertThat(bank.getUserName("54321"), is("No Such User"));
    }

    @Test
    public void whenCantFindUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        User expected = new User();
        assertThat(bank.getUser("55555").getName(), is(expected.getName()));
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        Account expected =  new Account(50, "1");
        assertThat(bank.getOneUserAccount("12345", "1"), is(expected));
    }

    @Test
    public void whenDeleteAccountFromUserThenNull() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
        Account expected = new Account();
        bank.deleteAccountFromUser("12345", bank.getOneUserAccount("12345", "2"));
        assertThat(bank.getOneUserAccount("12345", "2").getRequisites(), is(expected.getRequisites()));
    }

    @Test
    public void whenGetAllUserAccounts() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(60, "2"));
        List<Account> expected = new ArrayList<>();
        expected.add(new Account(50, "1"));
        expected.add(new Account(60, "2"));
        assertThat(bank.getUserAccounts("12345"), is(expected));
    }

    @Test
    public void whenGetOneOfUserAccount() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
        Account expected = new Account(60, "2");
        assertThat(bank.getOneUserAccount("12345", "2"), is(expected));
    }

    @Test
    public void whenTransferFromOneUserToAnotherThenTrue() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(50, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        boolean result = bank.transferMoney("12345", "123", "54321", "321", 10.0);
        assertThat(result, is(true));
    }

    @Test
    public void whenTransferFromOneUserToAnotherButNotEnoughThenFalse() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(10, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        boolean result = bank.transferMoney("12345", "123", "54321", "321", 20.0);
        assertThat(result, is(false));
    }

    @Test
    public void whenTransferFromOneUserToAnotherButCantFindThenFalse() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(50, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        boolean result = bank.transferMoney("12345", "555", "54321", "321", 20.0);
        assertThat(result, is(false));
    }
}
