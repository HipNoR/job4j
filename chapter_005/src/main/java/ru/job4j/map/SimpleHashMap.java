package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Simple HashMap realisation.
 * Stores information in a key-value pair.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 18.07.2018
 */
public class SimpleHashMap<K, V> {
    private Node<K, V>[] vault;

    /**
     * The default array capacity value.
     */
    final static int INITIAL_CAPACITY = 16;

    /**
     * Array load factor.
     */
    final static double LOAD_FACTOR = 0.75;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    private int threshold;

    /**
     * Number of elements in the array.
     */
    private int size = 0;

    /**
     * The number of changes in the array for the correct operation of the iterator.
     */
    private int modCount = 0;

    public SimpleHashMap() {
        vault = new Node[INITIAL_CAPACITY];
        reTresh();
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return vault.length;
    }

    /**
     * Calculates the value of the threshold.
     */
    private void reTresh() {
        threshold = (int) ((double) vault.length * LOAD_FACTOR);
    }

    /**
     * Inserts element in the array.
     * @param key of the element.
     * @param value of the element.
     * @return true if inserted or false if cell is busy.
     */
    public boolean insert(K key, V value) {
        boolean valid = false;
        int hash = hash(key);
        int i = (vault.length - 1) & hash;
        if (vault[i] == null) {
            vault[i] = new Node<>(hash, key, value);
            valid = true;
            modCount++;
            if (size++ >= threshold) {
                resize();
            }
        }
        return valid;

    }

    /**
     * Method resize the array if the threshold is exceeded.
     */
    private void resize() {
        Node<K, V>[] oldCont = vault;
        int oldCap = oldCont.length;
        Node<K, V>[] newCont = new Node[oldCap << 1];
        int newCap = newCont.length;
        int i;
        vault = newCont;
        reTresh();
        for (int index = 0; index < oldCap; index++) {
            if (oldCont[index] != null) {
                i = oldCont[index].hash & (newCap - 1);
                newCont[i] = oldCont[index];
            }
        }
        modCount++;
    }

    /**
     * Returns the value of an element by key.
     * @param key of item.
     * @return value of item.
     */
    public V get(K key) {
        int i = hash(key) & (vault.length - 1);
        return vault[i] == null ? null : vault[i].value;
    }

    /**
     * Removes the element by key.
     * @param key of value to be deleted.
     * @return true if exists and is removed else false.
     */
    public boolean delete(K key) {
        boolean valid = false;
        int i = hash(key) & (vault.length - 1);
        if (vault[i] != null) {
            vault[i] = null;
            size--;
            valid = true;
        }
        return valid;
    }

    /**
     * The method calculates the hash key value.
     * @param key to be hashed.
     * @return value of the hash.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public Iterator<Node<K, V>> iterator() {
       return new SimpleHashMap.Itr();
    }

    private class Itr implements Iterator<Node<K, V>> {

        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Counter changes in the container.
         */
        int expectedModCount = modCount;

        /**
         * Index + 1 of last returned element from the vault.
         */
        int position = 0;

        @Override
        public boolean hasNext() {
            checkForModifications();
            boolean valid = false;
            if (cursor < size) {
                valid = true;
            }
            return valid;
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Node<K, V> result = null;
            for (int index = position; index < vault.length; index++) {
                if (vault[index] != null) {
                    result = vault[index];
                    cursor++;
                    position = ++index;
                    break;
                }
            }
            return result;
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

    /**
     * Node for storage elements.
     * @param <K> type of key.
     * @param <V> type of value
     */
    private final class Node<K, V> {
        final int hash;
        final K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node<?, ?> node = (Node<?, ?>) o;

            if (!key.equals(node.key)) {
                return false;
            }
            return value.equals(node.value);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
