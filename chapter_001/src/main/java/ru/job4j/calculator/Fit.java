package ru.job4j.calculator;

/**
 * Class Fit - ideal weight calculator.
 * Calculates the ideal weight based on height.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Fit {

    /**
     * Ideal weight for man.
     * @param height height in cm.
     * @return ideal weight in kg.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Ideal weight for woman.
     * @param height height in cm.
     * @return ideal weight in kg.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
