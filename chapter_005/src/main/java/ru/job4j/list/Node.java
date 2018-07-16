package ru.job4j.list;

/**
 * Simple node class for linked list.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 12.07.2018
 */
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * The method determines whether there is a circular reference in the container.
     * Used Floyd's Tortoise and Hare algorithm.
     * @param node first element to start.
     * @return true if cycle else false.
     */
    boolean hasCycle(Node<T> node) {
        boolean valid = false;
        Node<T> slow = node;
        Node<T> fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                valid = true;
                break;
            }
        }
        return valid;
    }
}
