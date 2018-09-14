package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class FunctionInRangeTest {

    @Test
    public void whenLinearFromOneToSix() {
        FunctionInRange calc = new FunctionInRange();
        List<Double> result = calc.diapason(1, 6,
                x -> x
                );
        assertThat(result, is(Arrays.asList(1D, 2D, 3D, 4D, 5D)));
    }

    @Test
    public void whenSquaredFromOneToSix() {
        FunctionInRange calc = new FunctionInRange();
        List<Double> result = calc.diapason(1, 6,
                x -> x * x
        );
        assertThat(result, is(Arrays.asList(1D, 4D, 9D, 16D, 25D)));
    }

    @Test
    public void whenLogFromOneToThree() {
        FunctionInRange calc = new FunctionInRange();
        List<Double> result = calc.diapason(1, 3,
                Math::log
        );
        assertThat(result, is(Arrays.asList(0D, 0.6931471805599453D)));
    }
}