package ru.job4j.todolist.persistent;

import org.junit.Test;
import ru.job4j.todolist.models.Item;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MemoryStorageTest {

    @Test
    public void whenAddItemAndGetThanEquals() {
        Storage storage = MemoryStorage.getInstance();
        Item input = new Item();
        input.setDescript("test item");
        input.setCreated(new Timestamp(System.currentTimeMillis()));
        long id = storage.add(input);
        Item result = storage.getItemById(id);
        assertEquals(input, result);
        storage.clearList();
    }

    @Test
    public void whenDeleteItemAndItThanNull() {
        Storage storage = MemoryStorage.getInstance();
        Item input = new Item();
        input.setDescript("test item");
        input.setCreated(new Timestamp(System.currentTimeMillis()));
        long id = storage.add(input);
        storage.delete(input);
        assertNull(storage.getItemById(id));
    }

    @Test
    public void whenAddTwoItemsAndGetThemAllThenSortedByTimeAndTrue() {
        Storage storage = MemoryStorage.getInstance();
        Item testOne = new Item();
        testOne.setCreated(new Timestamp(System.currentTimeMillis()));
        storage.add(testOne);
        List<Item> result = storage.getAll();
        List<Item> expected = List.of(testOne);
        assertThat(result, is(expected));
        storage.clearList();
    }

    @Test
    public void whenClearListThanEmpty() {
        Storage storage = MemoryStorage.getInstance();
        Item testOne = new Item();
        testOne.setCreated(new Timestamp(System.currentTimeMillis()));
        storage.add(testOne);
        storage.clearList();
        List<Item> result = storage.getAll();
        List<Item> expected = new ArrayList<>();
        assertThat(result, is(expected));
    }
}