package ru.job4j.userstorage.persistent;

import java.util.List;

/**
 * Storage of users interface.
 * Persistent layout.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.10.2018
 */
public interface Store {

    boolean add(User user);

    boolean update(User user);

    boolean delete(long id);

    List<User> findAll();

    User findById(long id);
}
