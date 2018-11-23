package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory STORAGE class.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 23.11.2018
 */
public class PersonStorage {
    private final Logger logger = LoggerFactory.getLogger(PersonStorage.class);
    private static final PersonStorage STORAGE = new PersonStorage();
    private final ConcurrentHashMap<String, Person> store = new ConcurrentHashMap<>();

    private PersonStorage() {
    }

    public static PersonStorage getInstance() {
        return STORAGE;
    }

    public boolean add(Person person) {
        return store.put(person.getFirstname(), person) == null;
    }

    public List<Person> getAll() {
        List<Person> result = new ArrayList<>();
        result.addAll(store.values());
        return result;
    }
}
