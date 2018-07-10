package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class wrapper for the array.
 * The array has a fixed size.
 * @param <T> generic.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int position = 0;
    private int size = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
        this.size = size;
    }

    /**
     * Method add element to array.
     * If array is full - throws exception.
     * @param model object to be added.
     */
    public void add(T model) {
        outOfArray(this.position);
        this.objects[position++] = model;
    }

    /**
     * Replace element in array by another.
     * Throws an exception if the index is greater than the size.
     * @param index element to be replaced.
     * @param model the element that replaces.
     */
    public void set(int index, T model) {
        outOfArray(index);
        this.objects[index] = model;
    }

    /**
     * Gets the element from the array.
     * Throws an exception if the index is greater than the size.
     * @param index position of the element.
     * @return element in the index position.
     */
    public T get(int index) throws NoSuchElementException {
        outOfArray(index);
        if (this.objects[index] == null) {
            throw new NoSuchElementException();
        }
        return (T) this.objects[index];
    }

    /**
     * Removes an element at index.
     * Throws an exception if the index is greater than the size.
     * @param index of element to be deleted.
     */
    public void delete(int index) throws NoSuchElementException {
        outOfArray(index);
        if (index == size - 1) {
            objects[index] = null;
        } else {
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            objects[size - 1] = null;
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
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            boolean valid = false;
            if (cursor != size && objects[cursor] != null) {
                valid = true;
            }
            return valid;
        }

        @Override
        public T next() {
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
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            try {
                delete(lastRet);
                if (lastRet < cursor) {
                    cursor--;
                }
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }
}
