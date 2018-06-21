package ru.job4j.list;

import java.util.List;

/**
 * Class converter List to 2D array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class ConvertList2Array {
    /**
     * The method takes a list of numbers and converts them to an array.
     * @param list input list of integers.
     * @param rows number of rows.
     * @return 2d array of integers.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / (double) rows);
        int count = 0;
        int[][] array = new int[rows][cells];
        for (int out = 0; out < rows; out++) {
            for (int in = 0; in < cells; in++) {
                if (count >= list.size()) {
                    array[out][in] = 0;
                    count++;
                } else {
                    array[out][in] = list.get(count);
                    count++;
                }
            }
        }
        return array;
    }
}