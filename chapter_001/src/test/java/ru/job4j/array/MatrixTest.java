package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void when9on9() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(9);
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 4, 6, 8, 10, 12, 14, 16, 18},
                {3, 6, 9, 12, 15, 18, 21, 24, 27},
                {4, 8, 12, 16, 20, 24, 28, 32, 36},
                {5, 10, 15, 20, 25, 30, 35, 40, 45},
                {6, 12, 18, 24, 30, 36, 42, 48, 54},
                {7, 14, 21, 28, 35, 42, 49, 56, 63},
                {8, 16, 24, 32, 40, 48, 56, 64, 72},
                {9, 18, 27, 36, 45, 54, 63, 72, 81},
        };
        assertThat(table, is(expect));
    }
}