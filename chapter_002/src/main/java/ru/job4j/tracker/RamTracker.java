package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class Tracker repository of requests.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class RamTracker {
    /**
     * List of items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Object of random class.
     */
    private static final Random RN = new Random();

    /**
     * Add item to List.
     * @param item input object of class item.
     */
    public  Item add(Item item) {
        item.setId(generatedId());
        this.items.add(item);
        return item;
    }

    /**
     * Changes the item in the List by the id.
     * @param id input id.
     * @param item input Item.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                item.setId(id);
                items.set(index, item);
            }
        }
    }

    /**
     * Search and delete item by id.
     * @param id input id of item.
     */
    public boolean delete(String id) {
        boolean deleted = false;
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                this.items.remove(index);
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    /**
     * Return all elements.
     * @return list with all elements.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Searches for items by name and copies them to a list of names.
     * @param key input name to search.
     * @return list of elements with searched name.
     */
    public List<Item> findByName(String key) {
        return items.stream().filter(item -> item.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     * Searches for items by id.
     * @param id input id to searching.
     * @return found item.
     */
    public Item findById(String id) {
        Optional<Item> opt =  items.stream().filter(item -> item.getId().equals(id)).findFirst();
        return opt.orElse(null);
    }

    /**
     * Generates a random number for a unique ID.
     * @return generated ID.
     */
    private String generatedId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}