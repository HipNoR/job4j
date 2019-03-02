package ru.job4j.springannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.model.User;

import java.util.List;

/**
 * User storage bean.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.02.2019
 */
@Component
public class UserStorage {

    private final Storage<User> storage;

    @Autowired
    public UserStorage(final Storage<User> storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    public List<User> getUsers() {
        return this.storage.getAll();
    }
}
