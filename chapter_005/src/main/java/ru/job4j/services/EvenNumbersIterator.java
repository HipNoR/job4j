package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Iterator for even numbers in array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class EvenNumbersIterator implements Iterator {
    private final int[] array;
    private int position = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean valid = false;
        if (position < array.length) {
            for (int index = position; index < array.length; index++) {
                if (array[index] % 2 == 0) {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (array[position] % 2 != 0) {
            position++;
        }
        return array[position++];
    }
}
