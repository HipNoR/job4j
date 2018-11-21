package ru.job4j.userstorage.persistent;

import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MemoryStoreTest {
    MemoryStore store = MemoryStore.getInstance();


    @After
    public void clear() {
        store.deleteAll();
    }

    @Test
    public void whenAddTwoDifferentUsersThanSizeIsTwo() {
        store.add(new User(1, "first", "first login", "pass", "user",  "first@mail.ru"));
        store.add(new User(2, "second", "second login", "pass", "user", "second@mail.ru"));
        assertThat(store.findAll().size(), is(3));
    }

    @Test
    public void whenAddTwoUsersWithSameIdThanSizeIsOne() {
        store.add(new User(1, "first", "first login", "pass", "user",  "first@mail.ru"));
        store.add(new User(1, "second", "second login", "pass", "user", "second@mail.ru"));
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenUpdateUserThanUpdated() {
        store.add(new User(1, "first", "first login", "pass", "user",  "first@mail.ru"));
        store.update(new User(1, "updated first", "updated first login", "pass", "user",  "ufirst@mail.ru"));
        assertThat(store.findById(1).getName(), is("updated first"));
    }

    @Test
    public void whenDeleteUserThanDeleted() {
        store.add(new User(1, "first", "first login", "pass", "user",  "first@mail.ru"));
        assertTrue(store.delete(1));
        assertNull(store.findById(1));
    }
}