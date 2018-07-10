package ru.job4j.generic;

/**
 * Role class.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Role extends Base {

    protected Role(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("Role{ %s }", getId());
    }
}
