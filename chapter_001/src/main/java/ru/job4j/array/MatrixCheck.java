package ru.job4j.array;

/**
 * Class Matrix - checks the diagonal for equality of elements.
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
        boolean result = false;
        for (int index = 1; index != data.length; index++) {
            if (data[0] [0] == data[index] [index]
                    || data[data.length - 1][0] == data[data.length - index - 1][index]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}
