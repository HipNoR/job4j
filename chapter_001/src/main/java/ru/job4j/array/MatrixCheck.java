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
        if (fillBy(data, 1,  0)
                || fillBy(data, -1, data.length - 1)) {
            result = true;
        }
        return result;
    }

    private boolean fillBy(boolean[][] data, int deltaX, int startX) {
        boolean result = true;
        int startY = 0;
        int nextX = startX + deltaX;
        for (int index = 1; index < data.length; index++) {
            if (data[startX][startY] != data[nextX][index]) {
                result = false;
                break;
            }
            nextX += deltaX;
        }
        return result;
    }
}
