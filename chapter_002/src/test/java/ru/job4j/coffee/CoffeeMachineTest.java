package ru.job4j.coffee;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenInsertFiftyThenFifteen() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(50, 35);
        int[] expected = {10, 5};
        assertThat(result, is(expected));
    }

    @Test
    public void whenInsertHundredThenSixtyFive() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(100, 35);
        int[] expected = {10, 10, 10, 10, 10, 10, 5};
        assertThat(result, is(expected));
    }

    @Test
    public void whenInsertFiftyPriceIsThirtyNineThenEleven() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(50, 39);
        int[] expected = {10, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenInsertFiftyPriceIsTwentyEightTwentyTwo() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(50, 28);
        int[] expected = {10, 10, 2};
        assertThat(result, is(expected));
    }

    @Test
    public void whenInsertFiftyPriceIsThirtyEightThenThirteen() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(50, 37);
        int[] expected = {10, 2, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenInsertFiftyPriceIsThirtyTwoThenEighteen() {
        CoffeeMachine test = new CoffeeMachine();
        int[] result = test.changes(50, 32);
        int[] expected = {10, 5, 2, 1};
        assertThat(result, is(expected));
    }
}
