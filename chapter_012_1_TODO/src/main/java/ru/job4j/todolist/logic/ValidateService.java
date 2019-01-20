package ru.job4j.todolist.logic;

import ru.job4j.todolist.models.Item;
import ru.job4j.todolist.persistent.HDBStorage;
import ru.job4j.todolist.persistent.Storage;

import java.sql.Timestamp;
import java.util.List;

/**
 * The logical layer of the todolist web application.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.01.2019
 */
public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();

    private final Storage storage = HDBStorage.getInstance();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a list of all items in the storage.
     * @return elements.
     */
    public List<Item> getAll() {
        return storage.getAll();
    }

    /**
     * Adds an item to the storage.
     * @param item to be added.
     */
    public void addItem(Item item) {
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        storage.add(item);
    }

    /**
     * Changes the status of an item to completed.
     * @param index of element.
     */
    public void performTask(String index) {
        long id = Long.valueOf(index);
        Item target = storage.getItemById(id);
        target.setDone(true);
        storage.update(target);
    }
}
