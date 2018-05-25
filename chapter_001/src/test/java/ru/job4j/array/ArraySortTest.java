package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySortTest {
    @Test
    public void whenGetTwoSortedArraysThenPlusAndSort() {
        ArrayPlus plusSorted = new ArrayPlus();
        int[] inputOne = {1, 3};
        int[] inputTwo = {2, 4};
        int[] result = plusSorted.arrayPlus(inputOne, inputTwo);
        int[] expected = {1, 2, 3, 4};
        assertThat(result, is(expected));
    }

    @Test
    public void whenGetTwoSortedArraysThenPlusAndSortSecond() {
        ArrayPlus plusSorted = new ArrayPlus();
        int[] inputOne = {1, 3, 6, 7, 9};
        int[] inputTwo = {0, 2, 4, 5, 8};
        int[] result = plusSorted.arrayPlus(inputOne, inputTwo);
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));
    }
}
