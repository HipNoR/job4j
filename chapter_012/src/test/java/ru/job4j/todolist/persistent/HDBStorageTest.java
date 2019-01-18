package ru.job4j.todolist.persistent;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.todolist.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HDBStorageTest {

    @Before
    public void clearList() {
        HDBStorage storage = HDBStorage.getInstance();
        storage.clearList();
    }
    @Test
    public void whenAddItemAndGetItThanTrue() {
        HDBStorage storage = HDBStorage.getInstance();
        Item testItem = new Item();
        testItem.setDescript("test item");
        testItem.setCreated(new Timestamp(System.currentTimeMillis()));
        long id = storage.add(testItem);
        Item result = storage.getItemById(id);
        assertThat(testItem, is(result));
        storage.clearList();
    }

    @Test
    public void whenAddItemAndDeleteThemThenNull() {
        HDBStorage storage = HDBStorage.getInstance();
        Item testItem = new Item();
        testItem.setDescript("test item");
        testItem.setCreated(new Timestamp(System.currentTimeMillis()));
        long id = storage.add(testItem);
        storage.clearList();
        assertNull(storage.getItemById(id));

    }

    @Test
    public void whenUpdateThanUpdated() {
        HDBStorage storage = HDBStorage.getInstance();
        Item testItem = new Item();
        testItem.setDescript("test item");
        testItem.setCreated(new Timestamp(System.currentTimeMillis()));
        long id = storage.add(testItem);
        testItem.setDescript("updated item");
        storage.update(testItem);
        Item result = storage.getItemById(id);
        assertEquals(testItem, result);
        storage.clearList();
    }

    @Test
    public void whenAddAndGetAllThanTrue() {
        HDBStorage storage = HDBStorage.getInstance();
        Item testItem = new Item();
        testItem.setDescript("test item");
        testItem.setCreated(new Timestamp(System.currentTimeMillis()));
        storage.add(testItem);
        List<Item> result = storage.getAll();
        List<Item> expected = List.of(testItem);
        assertThat(result, is(expected));
        storage.clearList();
    }

}