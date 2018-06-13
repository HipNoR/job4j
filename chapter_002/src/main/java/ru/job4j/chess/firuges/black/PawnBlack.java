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
public class PawnBlack extends Figure {

    public PawnBlack(final Cell position) {
        super(position);
    }


    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        //boolean move = false;
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest };
          //  move = true;
        }
      //  if (!move) {
      //      throw new ImpossibleMoveException();
      //  }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
