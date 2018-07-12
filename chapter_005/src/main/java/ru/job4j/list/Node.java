package ru.job4j.list;

/**
 * Simple node class for linked list.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
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
     * @param node first element to start.
     * @return true if cycle else false.
     */
    boolean hasCycle(Node<T> node) {
        boolean valid = false;
        String loop = "";
        Node<T> temp = node;
        while (temp.next != null) {
            loop += temp.toString();
            temp = temp.next;
            if (loop.contains(temp.toString())) {
                valid = true;
                break;
            }
        }
        return valid;
    }
}
