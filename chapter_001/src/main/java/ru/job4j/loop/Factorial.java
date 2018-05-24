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
     * @param n input the number.
     * @return factorial of the number n.
     */
    public int calc(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
}
