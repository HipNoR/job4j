package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    private RoleStore store;

    @Before
    public void create() {
        store = new RoleStore();
        store.add(new Role("First"));
        store.add(new Role("Second"));
        store.add(new Role("Third"));
    }

    @Test
    public void whenAddThenAdded() {
        store.add(new Role("Added"));
        assertThat(store.byIndex(3).getId(), is("Added"));
    }

    @Test
    public void whenReplaceByIdThanReplaced() {
        Role replacer = new Role("Replaced");
        assertThat(store.replace("Second", replacer), is(true));
        assertThat(store.byIndex(1).getId(), is("Replaced"));
    }

    @Test
    public void delete() {
        assertThat(store.delete("Second"), is(true));
        assertThat(store.byIndex(1).getId(), is("Third"));
    }

    @Test
    public void findById() throws Exception {
        Role expected = new Role("Second");
        assertThat(store.findById("Second").getId(), is(expected.getId()));
    }

}