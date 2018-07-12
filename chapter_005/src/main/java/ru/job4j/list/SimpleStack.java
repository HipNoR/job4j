package ru.job4j.list;

/**
 * Linked list implements principle last input first output.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 12.07.2018
 */
public class SimpleStack<T> extends SimpleLinkedList {

    /**
     * Add item to list.
     * @param item to be added.
     */
    public void push(T item) {
        add(item);
    }

    /**
     * Method gets the last element in the list.
     * After delete it.
     * @return value of the last item.
     */
    public T poll() {
        T result = (T) getLast();
        remove(size() - 1);
        return result;
    }
}
