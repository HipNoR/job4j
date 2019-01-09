package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class converter List to 2D array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.2
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
        int[][] array = new int[rows][cells];
        int out = 0;
        int in = 0;
        for (int i : list) {
            if (in == cells) {
                out++;
                in = 0;
            }
            array[out][in++] = i;
        }
        return array;
    }

    /**
     * Converts list of Arrays to list of integers.
     * @param list input List of arrays.
     * @return List of Integers.
     */
    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}