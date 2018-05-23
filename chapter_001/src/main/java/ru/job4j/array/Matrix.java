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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i] [j] = (i + 1)  * (j + 1);
            }
        }
        return table;
    }
}