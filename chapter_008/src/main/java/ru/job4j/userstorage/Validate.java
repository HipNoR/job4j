package ru.job4j.userstorage;


import java.util.List;

/**
 * Validate interface.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 20.11.2018
 */
public interface Validate {

    String doAction(final Action.Type action, final User user);

    List<User> findAll();

    User findById(long id);

    long isRegistered(String login, String password);
}
