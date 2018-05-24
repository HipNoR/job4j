package ru.job4j.array;

/**
 * Class Matrix - ----
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class MatrixCheck {
    /**
     * Method checks the diagonal for equality of elements.
     * @param data input array.
     * @return true or false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int size; // variable stores the size of the smaller side of the array
        if (data.length > data[0].length) {
            size = data[0].length;
        } else {
            size = data.length;
        }
        for (int index = 1; index != size; index++) {
            if (data[0] [0] != data[index] [index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
