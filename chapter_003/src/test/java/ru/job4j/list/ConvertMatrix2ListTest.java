package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expected = List.of(
                1, 2, 3, 4
        );
        assertThat(list.toList(input), is(expected));
    }

    @Test
    public void when3on3ArrayThenList9() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 3},
                {0, 4, 5},
                {6, 0, 7}
        };
        List<Integer> expected = List.of(
                1, 2, 3, 0, 4, 5, 6, 0, 7
        );
        assertThat(list.toList(input), is(expected));
    }
}