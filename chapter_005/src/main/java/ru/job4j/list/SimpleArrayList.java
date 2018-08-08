package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Array list class.
 * Dynamically expandable storage container for objects.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 11.07.2018
 */
@ThreadSafe
public class SimpleArrayList<T> {
    /**
     * Container for objects.
     */
    @GuardedBy("this")
    private Object[] container;

    /**
     * The number of elements in the container.
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

    /**
     * Method returns the number of items in the list.
     * @return number of elements in the list.
     */
    public int size() {
        return this.position;
    }

    /**
     * Method returns the current size of the container.
     * @return size of the container.
     */
    public int capacity() {
        return this.size;
    }

    /**
     * Method add element to array.
     * Method @ensureCapacity@ increments the modCount.
     * @param model object to be added.
     */
    public synchronized void add(T model) {
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
            container = Arrays.copyOf(container, newCapacity);
            this.size = newCapacity;
            modCount++;
        }
    }

    /**
     * Replace element in array by another.
     * @param index element to be replaced.
     * @param model the element that replaces.
     * @throws IndexOutOfBoundsException if index greater than list size.
     */
    public synchronized void set(int index, T model) {
        outOfNumberOfElements(index);
        this.container[index] = model;
        modCount++;
    }

    /**
     * Gets the element from the array.
     * Throws an exception if the index is greater than the size.
     * @param index position of the element.
     * @return element in the index position.
     * @throws IndexOutOfBoundsException if index greater than number of elements in list.
     */
    public T get(int index) {
        outOfNumberOfElements(index);
        return (T) this.container[index];
    }

    /**
     * Removes an element at index.
     * @param index of element to be deleted.
     * @throws IndexOutOfBoundsException if index greater than number of elements.
     */
    public synchronized void delete(int index)  {
        outOfNumberOfElements(index);
        if (index == size - 1) {
            container[index] = null;
            position--;
            modCount++;
        } else {
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            container[size - 1] = null;
            position--;
            modCount++;
        }
    }

    /**
     * Checks the index greater than number of the elements.
     * @param index to check.
     * @throws IndexOutOfBoundsException if index greater than number of elements.
     */
    private void outOfNumberOfElements(int index) throws IndexOutOfBoundsException {
        if (index >= position) {
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
         * Counter changes in the container.
         */
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            checkForModifications();
            boolean valid = false;
            if (cursor < position) {
                valid = true;
            }
            return valid;
        }

        @Override
        public T next() throws IndexOutOfBoundsException {
            checkForModifications();
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            int i = cursor;
            T next = get(i);
            lastRet = i;
            cursor = i + 1;
            return next;
        }

        @Override
        public void remove() throws IllegalStateException, IndexOutOfBoundsException {
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
                throw new IndexOutOfBoundsException();
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
