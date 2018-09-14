package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Simple lambda task.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 14.09.2018
 */
public class Calculator {

    public void multiply(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op, Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiply(
                0, 10, 2,
                (left, right) -> {
                    double result = left * right;
                    System.out.printf("Multiple %s * %s = %s %n", left, right, result);
                    return result;
                },
                result -> System.out.println(result)
        );
    }
}
