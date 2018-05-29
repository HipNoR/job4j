package ru.job4j.array;

/**
 * Class Matrix - create a multiplication table.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Matrix {
    /**
     * Method create create a multiplication table.
     * @param size - sets size of the table.
     * @return
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int out = 0; out < size; out++) {
            for (int in = 0; in < size; in++) {
                table[out] [in] = (out + 1)  * (in + 1);
            }
        }
        return table;
    }
}