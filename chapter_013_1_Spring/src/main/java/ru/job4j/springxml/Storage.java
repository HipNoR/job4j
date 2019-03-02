package ru.job4j.springxml;

import java.util.List;

/**
 * Storage interface.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.02.2019
 */
public interface Storage<T> {

    void add(T element);

    List<T> getAll();
}
