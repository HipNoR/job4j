package ru.job4j.bank;

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
     * Method adds the User to Bank map.
     * @param user
     */
    public void addUser(User user) {
        this.base.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.base.remove(user);
    }

    public User getUser(String passport) {
        User searched = new User();
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                searched = user;
                break;
            }
        }
        return searched;
    }

    public String getUserName(String passport) {
        String name = "No Such User";
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                name = user.getName();
                break;
            }
        }
        return name;
    }

    public void addAccountToUser(String passport, Account account) {
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                ArrayList<Account> temp = this.base.get(user);
                temp.add(account);
                this.base.replace(user, temp);
            }
        }

    }

    public void deleteAccountFromUser(String passport, Account account) {
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                ArrayList<Account> temp = this.base.get(user);
                temp.remove(account);
                this.base.replace(user, temp);
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        Set<User> keys = this.base.keySet();
        for (User user : keys) {
            if (user.getPassport().equals(passport)) {
                accounts = this.base.get(user);

            }
        }
        return accounts;
    }

    public Account getOneUserAccount(String passport, String requisite) {
        List<Account> accounts = getUserAccounts(passport);
        Account account = new Account();
        for (Account acc : accounts) {
            if (acc.getRequisites().equals(requisite)) {
                account = acc;
            }
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String destRequisite, double amount) {
        Account output = getOneUserAccount(srcPassport, srcRequisite);
        Account input = getOneUserAccount(destPassport, destRequisite);
        return output.transfer(output, input, amount);
    }

    @Override
    public String toString() {
        return String.format("Accounts [%s]", this.base);
    }


}
