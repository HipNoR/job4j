package ru.job4j.generic;

/**
 * User class.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class User extends Base {

    protected User(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("User{ %s }", getId());
    }
}
