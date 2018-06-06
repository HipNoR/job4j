package ru.job4j.tracker;

import java.util.Random;

/**
 * Class Tracker repository of requests.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Tracker {
    /**
     * Array if items.
     */
    private Item[] items = new Item[100];

    /**
     * Index of new item in array.
     * Object of random class.
     */
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Add item to array.
     * @param item input object of class item.
     */
    public  Item add(Item item) {
        item.setId(generatedId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Changes the item in the array by the id.
     * @param id input id.
     * @param item input Item.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                item.setId(id);
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Search and delete item by id.
     * @param id input id of item.
     */
    public boolean delete(String id) {
        int start;
        boolean deleted = false;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                start = index;
                System.arraycopy(this.items, start + 1, this.items, start, this.position - start - 1);
                this.items[this.position--] = null;
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    /**
     * Finds all not null elements.
     * @return copy of array with all elements.
     */
    public Item[] findAll() {
        Item[] found = new Item[this.position];
        System.arraycopy(this.items, 0, found, 0, this.position);
        return found;
    }

    /**
     * Searches for items by name and copies them to an array - a list of names.
     * @param key input name to search.
     * @return list of elements with searched name.
     */
    public Item[] findByName(String key) {
        Item[] temp = new Item[this.position];
        int count = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                temp[count++] = this.items[index];
            }
        }
        Item[] listOfNames = new Item[count];
        System.arraycopy(temp, 0, listOfNames, 0, count);
        return  listOfNames;
    }

    /**
     * Searches for items by id.
     * @param id input id to searching.
     * @return found item.
     */
    public Item findById(String id) {
        Item result = null;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                result = this.items[index];
                break;
            }
        }
        return result;
    }

    /**
     * Generates a random number for a unique ID.
     * @return generated ID.
     */
    private String generatedId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
