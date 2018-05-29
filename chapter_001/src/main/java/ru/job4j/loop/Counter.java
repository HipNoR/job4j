package ru.job4j.loop;

/**
 * Class Counter returns the sum of even numbers in the range.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Counter {
    /**
     * Method adds even numbers in the range.
     * @param start the first number in the range.
     * @param finish the last number in the range.
     * @return the sum of even numbers in the range
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                sum += index;
            }
        }
        return sum;
    }
}
