package ru.job4j.bank;

import org.junit.Test;
import ru.job4j.bank.exceptions.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {
    @Test
    public void whenUserAlreadyExistThenException() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        try {
            bank.addUser(new User("Roman", "12345"));
        } catch (UserAlreadyExistException uae) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void whenTryAddExistedAccountThenException() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        try {
            bank.addAccountToUser("12345", new Account(50, "1"));
        } catch (AccountAlreadyExistException aae) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void whenAddTwoUsers() {
        Bank bank = new Bank();
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        assertThat(bank.getUser("54321").getName(), is("Ivan"));
    }

    @Test
    public void whenAddThreeUsersThenDeleteOne() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addUser(new User("Oleg", "55555"));
        bank.deleteUser(bank.getUser("54321"));
        try {
            bank.getUser("54321");
        } catch (NoSuchUserException nse) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void whenCantFindUser() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        try {
           bank.getUser("55555");
        } catch (NoSuchUserException nse) {
            thrown = true;
        }
        assertTrue(thrown);
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
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
        bank.deleteAccountFromUser("12345", bank.getOneUserAccount("12345", "2"));
        try {
            bank.getOneUserAccount("12345", "2");
        } catch (NoSuchAccountException nsa) {
            thrown = true;
        }
        assertTrue(thrown);
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
    public void whenGetAllUserAccountsButNoSuchUser() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(60, "2"));
        try {
            bank.getUserAccounts("5555");
        } catch (NoSuchUserException nsu) {
            thrown = true;
        }
        assertTrue(thrown);
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
    public void whenGetOneOfUserAccountButCantFound() {
        Bank bank = new Bank();
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addAccountToUser("12345", new Account(50, "1"));
        bank.addAccountToUser("12345", new Account(50, "2"));
        try {
            bank.getOneUserAccount("12345", "55");
        } catch (NoSuchAccountException nsa) {
            thrown = true;
        }
        assertTrue(thrown);
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
        boolean thrown = false;
        bank.addUser(new User("Roman", "12345"));
        bank.addUser(new User("Ivan", "54321"));
        bank.addAccountToUser("12345", new Account(50, "123"));
        bank.addAccountToUser("54321", new Account(50, "321"));
        try {
            bank.transferMoney("12345", "555", "54321", "321", 20.0);
        } catch (NoSuchAccountException nsa) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
