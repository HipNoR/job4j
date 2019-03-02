package ru.job4j.springannotations;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory storage bean
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version $
 * @since 0.1
 * 17.02.2019
 */
@Component
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
