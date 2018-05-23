package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenGetArrayThenSort() {
        BubbleSort sortIt = new BubbleSort();
        int[] data = new int[] {3, 5, 2, 4, 1, 0, 6, 9, 7, 8};
        int[] value = sortIt.sort(data);
        int[] expected = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(value, is(expected));
    }
}
