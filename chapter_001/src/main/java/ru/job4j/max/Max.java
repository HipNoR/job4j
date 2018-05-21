package ru.job4j.max;

/**
 * Class Max - finds of maximum value from two.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Max {
    /**
     * Method returns maximum from two.
     * @param first - first value.
     * @param second - second value.
     * @return maximum value.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Method returns maximum from three.
     * @param first - first value.
     * @param second - second value.
     * @param third - third value.
     * @return
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        temp = this.max(temp, third);
        return temp;
    }
}
