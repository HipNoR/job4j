package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {
    @Test
    public void whenArrayHasLength5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenArrayHasLength9ThenMinusOne() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 9;
        int result = find.indexOf(input, value);
        int expected = -1;
        assertThat(result, is(expected));
    }

}
