package ru.job4j.list;

/**
 * Simple linked list class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 11.07.2018
 */
public class SimpleOneWayLinkedList<E> {

    private int size;
    private Node<E> first;

    /**
     * Method adds an element to the head of the list.
     * @param date
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Method deletes an element at the head of the list.
     * @return deleted element.
     */
    public E delete() {
        E result = first.date;
        first = first.next;
        this.size--;
        return result;
    }

    /**
     * Method gets the element by index.
     * @param index to get.
     * @return element.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Method gets the size of the collections.
     * @return size of collection.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class for data storage.
     * @param <E> type.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
