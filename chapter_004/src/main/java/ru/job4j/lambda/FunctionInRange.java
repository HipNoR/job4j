package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A simple function is computed in a range.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 14.09.2018
 */
public class FunctionInRange {

    public List<Double> diapason(int start, int finish, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int index = start; index != finish; index++) {
            result.add(
                func.apply((double) index)
            );
        }
        return result;
    }

}
