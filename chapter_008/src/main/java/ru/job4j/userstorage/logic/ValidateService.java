package ru.job4j.userstorage.logic;

import ru.job4j.userstorage.persistent.DbStore;
import ru.job4j.userstorage.persistent.Store;
import ru.job4j.userstorage.persistent.User;

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
 * @version 0.4$
 * @since 0.1
 * 31.10.2018
 */

public class ValidateService implements Validate {

    /**
     * Instance of storage class.
     */
    private final Store store = DbStore.getInstance();

    /**
     * Actions storage.
     */
    private final Map<Action.Type, Function<User, String>> dispatch = new HashMap<>();

    private ValidateService() {
    }

    /**
     * On demand holder.
     */
    public static class ValidateStubHolder {
        public static final ValidateService HOLDER_INSTANCE = new ValidateService().init();
    }

    /**
     * This method returns a single instance of the class.
     * @return instance of class.
     */
    public static Validate getInstance() {
        return ValidateStubHolder.HOLDER_INSTANCE;
    }

    /**
     * Add new User to storage.
     * @return message to logic layout.
     */
    private Function<User, String> add() {
        return user -> {
            String result = "User already exists";
            if (store.add(user)) {
                result = String.format("User with id %s was added.", user.getId());
            }
            return result;
        };
    }

    /**
     * Changes user if it exists.
     * @return message to logic layout.
     */
    private Function<User, String> update() {
        return user -> {
            String result = "No such user!";
            if (store.update(user)) {
                result = String.format("User with id %s was updated", user.getId());
            }
            return result;
        };
    }

    /**
     * Removes user by id.
     * @return message to logic layout.
     */
    private Function<User, String> delete() {
        return user -> {
            String result = "No such user!";
            if (store.delete(user.getId())) {
                result = String.format("User with id %s was deleted", user.getId());
            }
            return result;
        };
    }

    /**
     * Init dispatcher.
     * @return current object.
     */
    private ValidateService init() {
        this.load(Action.Type.ADD, this.add());
        this.load(Action.Type.UPDATE, this.update());
        this.load(Action.Type.DELETE, this.delete());
        return this;
    }

    /**
     * Load handlers for actions.
     * @param type action key.
     * @param handle Handler.
     */
    private void load(Action.Type type, Function<User, String> handle) {
        this.dispatch.put(type, handle);
    }

    /**
     *
     * @param action to do with user.
     * @param user for action.
     * @return report on the work done.
     */
    public String doAction(final Action.Type action, final User user) {
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

    /**
     * The method checks if the user exists in the store.
     * @param login to search.
     * @param password to search.
     * @return id if exists else -1;
     */
    public long isRegistered(String login, String password) {
        long id = -1;
        for (User user : store.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                id = user.getId();
                break;
            }
        }
        return id;
    }
}

