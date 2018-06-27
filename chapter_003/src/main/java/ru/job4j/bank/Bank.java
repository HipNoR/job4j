package ru.job4j.bank;

import ru.job4j.bank.exceptions.*;

import java.util.*;

/**
 * Bank class.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Bank {
    private Map<User, ArrayList<Account>> base = new TreeMap<>();

    /**
     * Method adds the User to Bank base map.
     * Method putIfAbsent adds a user if it does not exist in this map.
     * @param user will be added in base.
     * @throws UserAlreadyExistException When try to add an existing user.
     */
    public void addUser(User user) throws UserAlreadyExistException {
        if (this.base.putIfAbsent(user, new ArrayList<>()) != null) {
            throw new UserAlreadyExistException("User already exist!");
        }
    }

    /**
     * Delete user from base.
     * @param user input user.
     */
    public void deleteUser(User user) {
        this.base.remove(user);
    }

    /**
     * Method searches the user for the passport number.
     * @param passport input passport number.
     * @return found user.
     * @throws NoSuchUserException if can't find this user.
     */
    public User getUser(String passport) throws NoSuchUserException {
        User searched = new User();
        boolean valid = false;
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                searched = user;
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new NoSuchUserException("No Such User!");
        }
        return searched;
    }

    /**
     * Method adds account to the user.
     * @param passport passport number of user.
     * @param account account to add.
     * @throws AccountAlreadyExistException when try to add an existing account.
     */
    public void addAccountToUser(String passport, Account account) throws AccountAlreadyExistException {
        ArrayList<Account> temp = this.base.get(getUser(passport));
        if (temp.indexOf(account) != -1) {
            throw new AccountAlreadyExistException("Account already exist!");
        }
        temp.add(account);
    }

    /**
     * Method deletes the account from the list of user accounts.
     * @param passport passport number of user.
     * @param account the account that will be deleted.
     * @throws NoSuchAccountException if can't find this account.
     */
    public void deleteAccountFromUser(String passport, Account account) throws NoSuchAccountException {
        ArrayList<Account> temp = this.base.get(getUser(passport));
        if (temp.indexOf(account) < 0) {
            throw new NoSuchAccountException("No such account!");
        }
        temp.remove(account);
    }

    /**
     * Method get the list of all user accounts.
     * @param passport passport number of user.
     * @return list of all user accounts.
     */
    public List<Account> getUserAccounts(String passport) {
        return this.base.get(getUser(passport));
    }

    /**
     * Method returns one account from the user.
     * @param passport passport number of user.
     * @param requisite requisites of the account.
     * @return found account.
     * @throws NoSuchAccountException if can't find this account.
     */
    public Account getOneUserAccount(String passport, String requisite) throws NoSuchAccountException {
        List<Account> accounts = getUserAccounts(passport);
        int index = accounts.indexOf(new Account(0, requisite));
        if (index < 0) {
            throw new NoSuchAccountException("No such account!");
        }
        return accounts.get(index);
    }

    /**
     * The method transfers money from the account of the first user to another account of another user.
     * @param srcPassport  the passport number of the sending user's money.
     * @param srcRequisite requisites of the sending user's money.
     * @param destPassport the passport number of the user receiving the money.
     * @param destRequisite requisites of the user receiving the money.
     * @param amount sum to transfer.
     * @return true or false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean valid = false;
        Account src = getOneUserAccount(srcPassport, srcRequisite);
        Account dst = getOneUserAccount(destPassport, destRequisite);
        if (src != null && dst != null) {
                src.subAmount(amount);
                dst.addAmount(amount);
                valid = true;
        }
        return valid;
    }

    @Override
    public String toString() {
        return String.format("Accounts [%s]", this.base);
    }
}
