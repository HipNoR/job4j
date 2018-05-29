package ru.job4j.array;

/**
 * Class Turn inverts an array .
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Turn {
    /**
     * Method inverts an array.
     * @param array input array.
     * @return reverted array.
     */
    public int[] turn(int[] array) {
        int temp;
        for (int index = 0; index < array.length / 2; index++) {
            temp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = temp;
        }
        return array;
    }
}
