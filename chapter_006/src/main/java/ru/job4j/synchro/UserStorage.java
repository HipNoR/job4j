package ru.job4j.synchro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadSafe class storage of users.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.08.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();


    public synchronized boolean add(User user) {
        boolean valid = false;
        if (!storage.contains(user)) {
            storage.add(user);
            valid = true;
        }
        return valid;
    }

    public synchronized boolean update(User user) {
        boolean valid = false;
        int position = searchIndexByUser(user);
        if (position != -1) {
            valid = true;
            storage.set(position, user);
        }
        return valid;
    }

    public synchronized boolean delete(User user) {
        boolean valid = false;
        int position = searchIndexByUser(user);
        if (position != -1) {
            valid = true;
            storage.remove(position);
        }
        return valid;
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        boolean valid = false;
        int firstId = searchIndexById(fromId);
        int secondId = searchIndexById(told);
        if ((firstId != -1 || secondId != -1) && storage.get(firstId).getAmount() >= amount) {
            User first = storage.get(firstId);
            User second = storage.get(secondId);
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
            valid = true;
        }
        return valid;
    }

    public  User searchUserById(int id) {
        int position = searchIndexById(id);
        if (id != -1) {
            return storage.get(position);
        }
        return null;
    }

    public int size() {
        return storage.size();
    }

    private int searchIndexByUser(User user) {
        int result = -1;
        if (storage.contains(user)) {
            for (int index = 0; index < storage.size(); index++) {
                if (user.equals(storage.get(index))) {
                    result = index;
                    break;
                }
            }
        }
        return result;
    }

    private int searchIndexById(int id) {
        int valid = -1;
        for (int index = 0; index < storage.size(); index++) {
            if (id == storage.get(index).getId()) {
                valid = index;
                break;
            }
        }
        return valid;
    }

    public void printStorage() {
        for (int index = 0; index < size(); index++) {
            System.out.println(String.format("User = id: %s, amount: %s", storage.get(index).getId(),
                    storage.get(index).getAmount()));
        }
    }

}
