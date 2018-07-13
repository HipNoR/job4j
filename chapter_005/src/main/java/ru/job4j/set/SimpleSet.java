package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

/**
 * Set class based on SimpleArrayList class.
 * All items are unique.
 * @see SimpleArrayList
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 13.07.2018
 */
public class SimpleSet<T> implements Iterable<T> {

    private SimpleArrayList<T> container;

    public SimpleSet() {
        this.container = new SimpleArrayList();
    }

    public SimpleSet(int size) {
        this.container = new SimpleArrayList(size);
    }

    /**
     * Method returns the number of elements in the set.
     * @return the number of elements in the set.
     */
    public int size() {
        return this.container.size();
    }

    /**
     * Method add elements in set.
     * If set already contains an equal element, the new element will not be added.
     * @param item to be added.
     */
    public void add(T item) {
        boolean repeat = false;
        for (int index = 0; index < size(); index++) {
            if (item.equals(this.container.get(index))) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            this.container.add(item);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.container.iterator();
    }
}
