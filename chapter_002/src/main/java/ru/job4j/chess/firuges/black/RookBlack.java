package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack extends Figure {

    public RookBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        int moveX = Math.abs(source.x - dest.x);
        int moveY = Math.abs(source.y - dest.y);
        if (source.y == dest.y + moveY && source.x == dest.x) {
            steps = new Cell[moveY];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y - moveY && source.x == dest.x) {
            steps = new Cell[moveY];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x, source.y + index + 1);
            }
            valid = true;
        } else if (source.y == dest.y && source.x == dest.x + moveX) {
            steps = new Cell[moveX];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x - index - 1, source.y);
            }
            valid = true;
        } else if (source.y == dest.y && source.x == dest.x - moveX) {
            steps = new Cell[moveX];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x + index + 1, source.y);
            }
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
