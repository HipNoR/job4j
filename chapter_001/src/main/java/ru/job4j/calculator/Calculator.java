package ru.job4j.calculator;

/**
 * Class Calculator - simple math calculator.
 * Realised addition, subtraction, division, multiplication.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Calculator {
    /**
     * Contain result of math operations in double type.
     */
    private double result;

    /**
     * Method addition of two numbers.
     * @param first - first number.
     * @param second - second number.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtraction of the first number into the second.
     * @param first - first number.
     * @param second - second number.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method division of the first number into the second.
     * @param first - first number.
     * @param second - second number.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method multiplication of two number.
     * @param first - first number.
     * @param second - second number.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     *Method returns result of the math operations.
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}

