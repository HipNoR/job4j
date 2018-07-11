package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array list class.
 * Dynamically expandable storage container for objects.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 11.07.2018
 */
public class SimpleArrayList<T> {
    /**
     * Container for objects.
     */
    private Object[] container;

    /**
     * Index on which item will be added.
     */
    private int position = 0;

    /**
     * The size of the container.
     */
    private int size = 0;

    /**
     * The count of modifications for the iterator working.
     */
    private int modCount = 0;

    public SimpleArrayList() {
        this.container = new Object[10];
        this.size = 10;
    }

    public SimpleArrayList(int capacity) {
        this.container = new Object[capacity];
        this.size = capacity;
    }

    public int size() {
        return this.size;
    }

    /**
     * Method add element to array.
     * If array is full - throws exception.
     * @param model object to be added.
     */
    public void add(T model) {
        ensureCapacity();
        this.container[position++] = model;
    }

    /**
     * Method for ensuring the container size.
     * When you add the last element to the current size, it doubles it.
     */
    private void ensureCapacity() {
        if (size == position) {
            int newCapacity = size * 2;
           // Object[] newArray = Arrays.copyOf(container, newCapacity);
            container = Arrays.copyOf(container, newCapacity);
            this.size = newCapacity;
            modCount++;
        }
    }

    /**
     * Replace element in array by another.
     * Throws an exception if the index is greater than the size.
     * @param index element to be replaced.
     * @param model the element that replaces.
     */
    public void set(int index, T model) {
        outOfArray(index);
        this.container[index] = model;
        modCount++;
    }

    /**
     * Gets the element from the array.
     * Throws an exception if the index is greater than the size.
     * @param index position of the element.
     * @return element in the index position.
     */
    public T get(int index) throws NoSuchElementException {
        outOfArray(index);
        if (this.container[index] == null) {
            throw new NoSuchElementException();
        }
        return (T) this.container[index];
    }

    /**
     * Removes an element at index.
     * Throws an exception if the index is greater than the size.
     * @param index of element to be deleted.
     */
    public void delete(int index) throws NoSuchElementException {
        outOfArray(index);
        if (index == size - 1) {
            container[index] = null;
            modCount++;
        } else {
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            container[size - 1] = null;
            modCount++;
        }

    }

    /**
     * Checks the index outside the array.
     * @param index to check.
     * @throws IndexOutOfBoundsException if index greater than arrays size.
     */
    private void outOfArray(int index) throws IndexOutOfBoundsException {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator.
     */
    public Iterator<T> iterator() {
        return new SimpleArrayList.Itr();
    }

    private class Itr implements Iterator<T> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;

        /**
         *
         */
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            checkForModifications();
            boolean valid = false;
            if (cursor != size && container[cursor] != null) {
                valid = true;
            }
            return valid;
        }

        @Override
        public T next() {
            checkForModifications();
            try {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                int i = cursor;
                T next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            checkForModifications();
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            try {
                delete(lastRet);
                    cursor = lastRet;
                expectedModCount = modCount;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        /**
         * Checks the container for changes after Iterator initialization.
         * @throws ConcurrentModificationException if modified.
         */
        private void checkForModifications() throws ConcurrentModificationException {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
