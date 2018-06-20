package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Class of Pawn figure black color.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class PawnBlack extends Figure {

    public PawnBlack(final Cell position) {
        super(position);
    }


    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return moveOnlyForwardOneStep(source, dest, true);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
