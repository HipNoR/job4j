package ru.job4j.userstorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Class for data validates.
 * Logic layout.
 * Checks data for compliance with conditions.
 * Based on Singleton pattern and Dispatch patter by Petr Arsentev (parsentev@uandex.ru).
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.10.2018
 */

public class ValidateService {
    /**
     * Single instance of the class with eager initialization.
     */
    private static ValidateService instance;

    /**
     * Instance of storage class.
     */
    private final Store store = MemoryStore.getInstance();

    /**
     * Actions storage.
     */
    private final Map<String, Function<User, String>> dispatch = new HashMap<>();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        if (instance == null) {
            synchronized (ValidateService.class) {
                if (instance == null) {
                    instance = new ValidateService();
                }
            }
        }
        return instance;
    }

    /**
     * Add new User to storage.
     * @return message to presentation layout.
     */
    public Function<User, String> add() {
        return user -> {
            String result = "User already exists";
            if (store.add(user)) {
                result = String.format("User with id: %s was added.", user.getId());
            }
            return result;
        };
    }

    /**
     * Changes user if it exists.
     * @return message to presentation layout.
     */
    public Function<User, String> update() {
        return user -> {
            String result = "No such user!";
            if (store.update(user)) {
                result = "User was updated";
            }
            return result;
        };
    }

    /**
     * Removes user by id.
     * @return message to presentation layout.
     */
    public Function<User, String> delete() {
        return user -> {
            String result = "No such user!";
            if (store.delete(user.getId())) {
                result = "User was deleted";
            }
            return result;
        };
    }

    /**
     * Init dispatcher.
     * @return current object.
     */
    public ValidateService init() {
        this.load("add", this.add());
        this.load("update", this.update());
        this.load("delete", this.delete());
        return this;
    }

    /**
     * Load handlers for actions.
     * @param action action key.
     * @param handle Handler.
     */
    public void load(String action, Function<User, String> handle) {
        this.dispatch.put(action, handle);
    }

    /**
     *
     * @param action to do with user.
     * @param user for action.
     * @return report on the work done.
     */
    public String doAction(final String action, final User user) {
        return this.dispatch.get(action).apply(user);
    }

    /**
     * Find all users in storage.
     * @return list of all users.
     */
    public List<User> findAll() {
        return store.findAll();
    }

    /**
     * Searches user by id.
     * @param id to search.
     * @return founded user.
     */
    public User findById(long id) {
        return store.findById(id);
    }
}

