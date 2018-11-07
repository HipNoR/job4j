package ru.job4j.userstorage;

/**
 * Actions interface.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 07.11.2018
 */
public interface Action {

    Type type();

    enum Type {
        ADD,
        UPDATE,
        DELETE
    }
}
