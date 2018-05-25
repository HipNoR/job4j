package ru.job4j.array;

/**
 * Class ArrayPlus - adds two arrays and sorts them.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class ArrayPlus {
    /**
     * arrayPlus puts two arrays in the third and sort its.
     * @param first - input first array.
     * @param second - input second array.
     * @return third sorted array.
     */
    public int[] arrayPlus(int[] first, int[] second) {
        int[] third = new int[first.length + second.length];
        for (int index = 0; index < first.length; index++) {
            third[index] = first[index];
        }
        for (int index = 0; index < second.length; index++) {
            third[index + first.length] = second[index];
        }
        BubbleSort sortIt = new BubbleSort();
        sortIt.sort(third);
        return third;
    }
}
