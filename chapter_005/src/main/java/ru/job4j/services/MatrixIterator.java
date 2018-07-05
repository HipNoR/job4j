package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Class Iterator for 2D array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class MatrixIterator implements Iterator {
    private final int[][] array;
    private int out = 0;
    private int in = 0;

    public MatrixIterator(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean valid = true;
        if (!(out < array.length && in < array[out].length)) {
            valid = false;
        }
        return valid;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = 0;
        if (in < array[out].length - 1) {
            result = this.array[out][in++];
        } else if (in == array[out].length - 1) {
            result = this.array[out++][in];
            in = 0;
        }
        return result;
    }
}
