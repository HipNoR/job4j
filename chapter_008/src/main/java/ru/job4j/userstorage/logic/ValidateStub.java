package ru.job4j.userstorage.logic;

import ru.job4j.userstorage.persistent.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Class stub for servlet tests.
 * The class copies the ValidatesService class, replacing the data store.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 20.11.2018
 */
public class ValidateStub implements Validate {

    private final ConcurrentHashMap<String, User> store = new ConcurrentHashMap<>();

    private final Map<Action.Type, Function<User, String>> dispatch = new HashMap<>();

    private ValidateStub() {
    }

    public static class ValidateStubHolder {
        public static final ValidateStub HOLDER_INSTANCE = new ValidateStub().init();
    }

    public static Validate getInstance() {
       return ValidateStubHolder.HOLDER_INSTANCE;
    }

    private Function<User, String> add() {
        return user -> {
            String result = "User already exists";
            if (store.put(String.valueOf(user.getId()), user) == null) {
                result = String.format("User with id %s was added.", user.getId());
            }
            return result;
        };
    }

    private Function<User, String> update() {
        return user -> {
            String result = "No such user!";
            if (store.replace(String.valueOf(user.getId()), user) != null) {
                result = String.format("User with id %s was updated", user.getId());
            }
            return result;
        };
    }

    private Function<User, String> delete() {
        return user -> {
            String result = "No such user!";
            if (store.remove(String.valueOf(user.getId())) != null) {
                result = String.format("User with id %s was deleted", user.getId());
            }
            return result;
        };
    }

    private ValidateStub init() {
        this.load(Action.Type.ADD, this.add());
        this.load(Action.Type.UPDATE, this.update());
        this.load(Action.Type.DELETE, this.delete());
        doAction(Action.Type.ADD, new User(0, "admin", "root", "root", "admin", "admin@mail"));
        return this;
    }

    private void load(Action.Type type, Function<User, String> handle) {
        this.dispatch.put(type, handle);
    }

    @Override
    public String doAction(Action.Type action, User user) {
        return this.dispatch.get(action).apply(user);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        result.addAll(store.values());
        return result;
    }

    @Override
    public User findById(long id) {
        return store.get(String.valueOf(id));
    }

    @Override
    public long isRegistered(String login, String password) {
        long id = -1;
        for (User user : findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                id = user.getId();
                break;
            }
        }
        return id;
    }
}
