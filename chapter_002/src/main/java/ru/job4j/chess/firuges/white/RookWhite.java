package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Class of Rook figure white color.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class RookWhite extends Figure {

    public RookWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return moveForward(source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
