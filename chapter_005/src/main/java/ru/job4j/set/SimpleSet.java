package ru.job4j.set;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Set class
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 13.07.2018
 */
public class SimpleSet<T> implements Iterable<T>{

    private Object[] container;
    private int size;
    private int position = 0;
    private int modCount = 0;

    public SimpleSet() {
        this.size = 10;
        container = new Object[size];
    }

    public SimpleSet(int size) {
        this.size = size;
        container = new Object[size];
    }

    public int size() {
        return this.position;
    }

    public void add(T t) {
        ensureCapacity();
        boolean repeat = false;
        for (int index = 0; index < position; index++) {
            if (t.equals(container[index])) {
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            container[position++] = t;
        }
    }

    private void ensureCapacity() {
        if (size == position) {
            int newCapacity = size * 2;
            container = Arrays.copyOf(container, newCapacity);
            this.size = newCapacity;
            modCount++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleSet.Itr();
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
            T next = (T) container[i];
            lastRet = i;
            cursor = i + 1;
            return next;
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
