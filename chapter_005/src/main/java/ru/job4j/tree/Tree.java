package ru.job4j.tree;

import java.util.*;

/**
 * Simple tree realisation.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 19.07.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * First element - aka root.
     */
    private Node<E> root;

    /**
     * The count of modifications for the iterator working.
     */
    private int modCount = 0;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Adds a child element to the parent element.
     * A parent element can contain a list of child elements.
     * If an item with this value already exists, it will return the false.
     * If the parent is not found, the false will return.
     * @param parent parent.
     * @param child child.
     * @return true if work is done, else false.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean valid = false;
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> result = findBy(parent);
            valid = result.isPresent();
            if (valid) {
                result.get().add(new Node<>(child));
                modCount++;
            }
        }
        return valid;
    }

    /**
     * Returns the node found by value.
     * @param value value.
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Checks that the tree is binary.
     * @return
     */
    public boolean isBinary() {
        boolean valid = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> childs = el.leaves();
            if (childs.size() > 2) {
                valid = false;
                break;
            }
            for (Node<E> child : childs) {
                data.offer(child);
            }
        }
        return valid;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Tree.Itr();
    }


    private class Itr implements Iterator<E> {

        /**
         * Counter changes in the container.
         */
        private int expectedModCount = modCount;

        /**
         * Container for all elements in the tree.
         */
        private  Queue<Node<E>> data = new LinkedList<>();

        private Itr() {
            data.offer(root);
        }

        @Override
        public boolean hasNext() {
            checkForModifications();
            return !data.isEmpty();
        }

        @Override
        public E next() {
            checkForModifications();
            Node<E> el;
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            } else {
                el = data.poll();
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            }
            return el.getValue();
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
