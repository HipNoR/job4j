package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Class of linked list.
 * Each element contains a value and a reference to the previous and next elements.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 12.07.2018
 */
public class SimpleLinkedList<T> {

    /**
     * The size of the container.
     */
    private int size = 0;

    /**
     * The count of modifications for the iterator working.
     */
    private int modCount = 0;

    /**
     * First element of the list.
     */
    private Node<T> first;

    /**
     * Last element of the list.
     */
    private Node<T> last;


    public SimpleLinkedList() {
    }

    /**
     * Method return number of elements in list.
     * @return number of elements in list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Add item at the last position.
     * @param item to be added.
     */
    public void add(T item) {
        Node<T> prev = last;
        Node<T> newNode = new Node<>(prev, item, null);
        last = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Method gets item by the index.
     * @param index of searched item;
     * @return item value.
     * @throws IndexOutOfBoundsException if index greater than number of elements.
     */
    public T get(int index) {
        outBounds(index);
        return getNode(index).item;
    }

    /**
     * Method gets the last element in list.
     * @return last element.
     */
    public T getLast() {
        return last.item;
    }

    /**
     * Method gets the first element in list.
     * @return first element.
     */
    public T getFirst() {
        return first.item;
    }

    /**
     * Delete the element in the list by the index.
     * @param index do be deleted.
     * @return value of deleted element.
     */
    public T remove(int index) {
        outBounds(index);
        T result = get(index);
        Node<T> temp = getNode(index);
        Node<T> prev = temp.prev;
        Node<T> next = temp.next;
        if (temp == first) {
            first = next;
        }
        if (temp == last) {
            last = prev;
        }
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        temp = null;
        size--;
        modCount++;
        return result;
    }

    /**
     * The method gets the node by the index.
     * @param index to search.
     * @return Node at index.
     */
    public Node<T> getNode(int index) {
        Node<T> result = null;
        Node<T> temp = first;
        for (int pos = 0; pos < size; pos++) {
            if (index == pos) {
                result = temp;
                break;
            }
            temp = temp.next;
        }
        return result;
    }

    /**
     * Checks the index greater than number of the elements.
     * @param index to check.
     * @throws IndexOutOfBoundsException if index greater than number of elements.
     */
    private void outBounds(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns an iterator over the items in this list in sequence.
     * @return an iterator.
     */
    public Iterator<T> iterator() {
        return new SimpleLinkedList.Itr();
    }

    private class Itr implements Iterator<T> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Counter changes in the container.
         */
        int expectedModCount = modCount;

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
        public T next() throws IndexOutOfBoundsException {
            checkForModifications();
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            int i = cursor;
            T next = get(i);
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

    /**
     * A class that stores the value of an element and a reference to the previous and next elements.
     * @param <T> generic.
     */
    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return String.format("Value is %s.", item);
        }
    }
}
