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
        for (int indexOne = array.length - 1; indexOne > 0; indexOne--) {
            for (int indexTwo = 0; indexTwo < indexOne; indexTwo++) {
                if (array[indexTwo] > array[indexTwo + 1]) {
                    temp = array[indexTwo];
                    array[indexTwo] = array[indexTwo + 1];
                    array[indexTwo + 1] = temp;
                }
            }
        }
        return array;
    }
}
