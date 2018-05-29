package ru.job4j.array;

/**
 * Class ArrayChar - sort values in ascending order.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class BubbleSort {
    /**
     * Method sort values in ascending order.
     * @param array input unsorted array.
     * @return sorted array.
     */
    public int[] sort(int[] array) {
        int temp;
        for (int out = array.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    temp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = temp;
                }
            }
        }
        return array;
    }
}
