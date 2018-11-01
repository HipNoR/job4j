package ru.job4j.userstorage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemoryStoreTest {
    MemoryStore store = MemoryStore.getInstance();


    @Test
    public void whenAddTwoDifferentUsersThanSizeIsTwo() {
        store.add(new User(1, "first", "first login", "first@mail.ru"));
        store.add(new User(2, "second", "second login", "second@mail.ru"));
        assertThat(store.findAll().size(), is(2));
        store.deleteAll();
    }

    @Test
    public void whenAddTwoUsersWithSameIdThanSizeIsOne() {
        store.add(new User(1, "first", "first login", "first@mail.ru"));
        store.add(new User(1, "second", "second login", "second@mail.ru"));
        assertThat(store.findAll().size(), is(1));
        store.deleteAll();
    }

    @Test
    public void whenUpdateUserThanUpdated() {
        store.add(new User(1, "first", "first login", "first@mail.ru"));
        store.update(new User(1, "updated first", "updated first login", "ufirst@mail.ru"));
        assertThat(store.findById(1).getName(), is("updated first"));
        store.deleteAll();
    }

    @Test
    public void whenDeleteUserThanDeleted() {
        store.add(new User(1, "first", "first login", "first@mail.ru"));
        assertTrue(store.delete(1));
        assertNull(store.findById(1));
        store.deleteAll();
    }
}