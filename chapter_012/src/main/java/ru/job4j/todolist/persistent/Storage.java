package ru.job4j.todolist.persistent;

import ru.job4j.todolist.models.Item;

import java.util.List;

/**
 * Storage interface.
 *
 * @see MemoryStorage concrete storage in RAM implementation.
 * @see HDBStorage specific storage in a PostgreSQL database using Hibernate.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 16.01.2019
 */
public interface Storage {
    /**
     * Adds an item to the storage.
     * @param item to be added.
     * @return generated id.
     */
    long add(Item item);

    /**
     * Updates item in the storage.
     * @param item to be updated.
     */
    void update(Item item);

    /**
     * Delete an item from the storage.
     * @param item to be deleted.
     */
    void delete(Item item);

    /**
     * The method returns an item by ID.
     * @param id for search.
     * @return item with tis id.
     */
    Item getItemById(long id);

    /**
     * Returns a list of all items in the storage.
     * @return list of all items.
     */
    List getAll();

    /**
     * Removes all items.
     */
    void clearList();
}
