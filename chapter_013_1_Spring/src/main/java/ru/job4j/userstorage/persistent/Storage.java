package ru.job4j.userstorage.persistent;

import ru.job4j.userstorage.entity.BaseEntity;

import java.util.List;

/**
 * Storage interface.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.02.2019
 */
public interface Storage<T extends BaseEntity> {

    void add(T bean);

    List<T> getAll();
}
