package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.black.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.chess.firuges.Cell.*;

public class LogicTest {

    @Test
    public void whenMoveRookThroughPawnWhenError() {
     Logic logic = new Logic();
        logic.add(new PawnBlack(H7));
        logic.add(new RookBlack(H8));
        boolean move = logic.move(H8, H5);
        assertThat(move, is(false));
    }

    @Test
    public void whenMoveRookToPawnPlaceWhenError() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(H7));
        logic.add(new RookBlack(H8));
        boolean move = logic.move(H8, H7);
        assertThat(move, is(false));
    }

    @Test
    public void whenMoveRookViaDiagonalWhenError() {
        Logic logic = new Logic();
        logic.add(new RookBlack(H8));
        boolean move = logic.move(H8, D4);
        assertThat(move, is(false));
    }

    @Test
    public void whenMoveRookViaFreeWayWhenMove() {
        Logic logic = new Logic();
        logic.add(new RookBlack(H8));
        boolean move = logic.move(H8, H4);
        assertThat(move, is(true));
    }

    @Test
    public void whenMoveEmptyCellThenException() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(H7));
        boolean move = logic.move(H6, H4);
        assertThat(move, is(false));
    }
}
