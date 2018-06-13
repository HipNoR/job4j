package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QueenBlack extends Figure {

    public QueenBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        int moveX = source.x - dest.x;
        int moveY = source.y - dest.y;
        if (source.y == dest.y + moveX && source.x == dest.x + moveX) {
            steps = new Cell[] {dest };
        } else if(source.y == dest.y + moveX && source.x == dest.x - moveX) {
            steps = new Cell[] {dest };
        } else if (source.y == dest.y - moveX && source.x == dest.x + moveX) {
            steps = new Cell[]{dest};
        } else if(source.y == dest.y - moveX && source.x == dest.x - moveX) {
            steps = new Cell[] {dest };
        } else  if (source.y == dest.y + moveY && source.x == dest.x) {
            steps = new Cell[] {dest };
        } else if(source.y == dest.y - moveY && source.x == dest.x) {
            steps = new Cell[] {dest };
        } else if (source.y == dest.y && source.x == dest.x + moveX) {
            steps = new Cell[]{dest};
        } else if(source.y == dest.y && source.x == dest.x - moveX) {
            steps = new Cell[] {dest };
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}
