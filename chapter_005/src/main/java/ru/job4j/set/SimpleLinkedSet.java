package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Set class based on SimpleLinkedList class.
 * All items are unique.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 13.07.2018
 */
public class SimpleLinkedSet<T> implements Iterable<T> {
    private SimpleLinkedList<T> container;

    public SimpleLinkedSet() {
        container = new SimpleLinkedList<>();
    }

    /**
     * Method add elements in set.
     * If set already contains an equal element, the new element will not be added.
     * @param item to be added.
     */
    public void add(T item) {
        boolean repeat = false;
        Iterator itr = container.iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(item)) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            container.add(item);
        }
    }

    /**
     * Method returns the number of elements in the set.
     * @return the number of elements in the set.
     */
    public int size() {
        return container.size();
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
