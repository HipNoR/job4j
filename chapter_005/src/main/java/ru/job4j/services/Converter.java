package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Iterator converter.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Converter {
    private Iterator<Integer> temp;

    /**
     *
     * @param it Iterator of Iterators<Integer>
     * @return Iterator of all elements.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        if (it.hasNext()) {
            temp = it.next();
            while (!temp.hasNext() && it.hasNext()) {
                temp = it.next();
            }
        }
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                boolean valid = false;
                if (temp.hasNext()) {
                    valid = true;
                }
                if (!temp.hasNext() && it.hasNext()) {
                    valid = true;
                }
                return valid;
            }
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!temp.hasNext() && it.hasNext()) {
                    temp = it.next();
                }
                return temp.next();
            }
        };
    }
}