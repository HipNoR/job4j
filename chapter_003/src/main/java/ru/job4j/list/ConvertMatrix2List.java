package ru.job4j.list;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class converter 2d Array to List.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class ConvertMatrix2List {
    /**
     *The method takes an array of numbers and converts them to a list.
     * @param array input 2d Array.
     * @return list with elements of Array.
     */
    public List<Integer> toList(int[][] array) {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}