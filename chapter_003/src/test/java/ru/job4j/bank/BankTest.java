package ru.job4j.bank;

import org.junit.Test;
import ru.job4j.bank.exceptions.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {
    @Test (expected = UserAlreadyExistException.class)
    public void whenUserAlreadyExistThenException() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Roman", "12345"));
    }

    @Test (expected = AccountAlreadyExistException.class)
    public void whenTryAddExistedAccountThenException() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "1"));
    }

    @Test
    public void whenAddTwoUsers() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        assertThat(bank.getUser("54321").getName(), is("Ivan"));
    }

    @Test (expected = NoSuchUserException.class)
    public void whenAddThreeUsersThenDeleteOne() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addUser(new User("Oleg", "55555"));
        bank.deleteUser(bank.getUser("54321"));
        bank.getUser("54321");
    }

    @Test (expected = NoSuchUserException.class)
    public void whenCantFindUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.getUser("55555");
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        Account expected =  new Account(50, "1");
        assertThat(bank.getOneUserAccount("12345", "1"), is(expected));
    }

    @Test (expected = NoSuchAccountException.class)
    public void whenDeleteAccountFromUserThenNull() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
        bank.deleteAccountFromUser("12345", bank.getOneUserAccount("12345", "2"));
        bank.getOneUserAccount("12345", "2");
    }

    @Test
    public void whenGetAllUserAccounts() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(60, "2"));
        List<Account> expected = List.of(
                new Account(50, "1"),
                new Account(60, "2")
        );
        assertThat(bank.getUserAccounts("12345"), is(expected));
    }

    @Test  (expected = NoSuchUserException.class)
    public void whenGetAllUserAccountsButNoSuchUser() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(60, "2"));
        bank.getUserAccounts("5555");
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

    @Test (expected = NoSuchAccountException.class)
    public void whenGetOneOfUserAccountButCantFound() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
            bank.getOneUserAccount("12345", "55");
    }

    @Test
    public void whenTransferFromOneUserToAnotherThenTrue() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(50, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        assertTrue(bank.transferMoney("12345", "123", "54321", "321", 10.0));
    }

    @Test
    public void whenTransferFromOneUserToAnotherButNotEnoughThenFalse() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(10, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        assertFalse(bank.transferMoney("12345", "123", "54321", "321", 20.0));
    }

    @Test (expected = NoSuchAccountException.class)
    public void whenTransferFromOneUserToAnotherButCantFindThenFalse() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(50, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        bank.transferMoney("12345", "555", "54321", "321", 20.0);
    }
}
