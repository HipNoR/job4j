package ru.job4j.array;

/**
 * Class Square operations with array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Square {
    /**
     * Method fills the array with elements equal to the squared indices.
     * @param bound - array length.
     * @return array.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int index = 1; index <= bound; index++) {
            rst[index - 1] = index * index;
        }
        return rst;
    }
}