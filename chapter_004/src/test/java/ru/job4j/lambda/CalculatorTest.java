package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CalculatorTest {

    @Test
    public void whenAdd1Until3() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiply(0, 3, 1,
                (value, index) -> (double) value + index,
                result -> buffer.add(result)
                );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }
}