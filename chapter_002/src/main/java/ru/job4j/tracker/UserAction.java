package ru.job4j.tracker;

/**
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();

}
