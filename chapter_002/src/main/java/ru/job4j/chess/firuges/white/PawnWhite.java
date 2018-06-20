package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Class of Pawn figure white color.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class PawnWhite extends Figure {

    public PawnWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return moveOnlyForwardOneStep(source, dest, false);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
