package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int[] result = turner.turn(input);
        int[] expected = new int[] {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turner = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5, 6};
        int[] result = turner.turn(input);
        int[] expected = new int[] {6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
