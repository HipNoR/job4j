package ru.job4j.array;

import java.util.Arrays;

/**
 * Class ArrayDuplicate
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class ArrayDuplicate {
    /**
     * Returns the cropped array without duplicates.
     * @param array input array.
     * @return array without duplicates.
     */
    public String[] remove(String[] array) {
        int count = array.length;
        for (int out = 0; out < count; out++) {
            for (int in = out + 1; in < count; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[count - 1];
                    count--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, count);
    }

}
