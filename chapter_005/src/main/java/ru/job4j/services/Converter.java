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

    /**
     *
     * @param it Iterator of Iterators<Integer>
     * @return Iterator of all elements.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> temp;
            Iterator<Iterator<Integer>> iterator = it;
            @Override
            public boolean hasNext() {
                boolean valid = false;
                if (temp == null || !temp.hasNext()) {
                    while (iterator.hasNext()) {
                        temp = iterator.next();
                        if (temp.hasNext()) {
                            valid = true;
                            break;
                        }
                    }
                } else if (temp.hasNext()) {
                    valid = true;
                }
                return valid;
            }
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (!temp.hasNext() && iterator.hasNext()) {
                    temp = iterator.next();
                }
                return temp.next();
            }
        };
    }
}