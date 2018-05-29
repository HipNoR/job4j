package ru.job4j.loop;

/**
 * Class Factorial.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Factorial {

    /**
     * Method calculates the factorial of a number.
     * @param number input the number.
     * @return factorial of the number.
     */
    public int calc(int number) {
        int sum = 1;
        for (int index = 1; index <= number; index++) {
            sum *= index;
        }
        return sum;
    }
}
