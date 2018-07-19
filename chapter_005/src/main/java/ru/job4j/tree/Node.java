package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Node for tree class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 19.07.2018
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    /**
     * Adds children to the list of children.
     * @param child to be added.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Returns a list of all the children of this parent.
     * @return
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Compares nodes.
     * @param that to be compared.
     * @return true or false.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Returns the value of the node.
     * @return value.
     */
    public E getValue() {
        return value;
    }
}