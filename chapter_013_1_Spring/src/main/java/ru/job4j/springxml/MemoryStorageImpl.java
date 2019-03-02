package ru.job4j.springxml;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of memory storage.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version $
 * @since 0.1
 * 17.02.2019
 */
public class MemoryStorageImpl<T> implements Storage<T> {

    private final List<T> storage = new ArrayList<>();

    @Override
    public void add(T element) {
        this.storage.add(element);
    }

    @Override
    public List<T> getAll() {
        return this.storage;
    }
}
