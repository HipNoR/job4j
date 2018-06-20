package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Abstract class of all figures.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public abstract class Figure {
    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell position() {
     return this.position;
    }

    public abstract Cell[] way(Cell source, Cell dest);

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    public Cell[] moveForward(Cell source, Cell dest) throws ImpossibleMoveException {
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

    public Cell[] moveDiagonal(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        int move = Math.abs(source.x - dest.x);
        if (source.y == dest.y + move && source.x == dest.x + move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x - index - 1, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y + move && source.x == dest.x - move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x + index + 1, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y - move && source.x == dest.x + move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x - index - 1, source.y + index + 1);
            }
            valid = true;
        } else if (source.y == dest.y - move && source.x == dest.x - move) {
            steps = new Cell[move];
            for (int index = 0; index < steps.length; index++) {
                steps[index] = Cell.findCell(source.x + index + 1, source.y + index + 1);
            }
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    public Cell[] moveOnlyForwardOneStep(Cell source, Cell dest, Boolean color) throws ImpossibleMoveException {
        boolean valid = false;
        int move = 0;  //If color true - color is black
        if (color) {
            move = 1;
        }
        if (!color) {  // if color false - color is white
            move = -1;
        }
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + move && source.x == dest.x) {
            steps = new Cell[] {dest };
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    public Cell[] moveZigZag(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        if ((source.y == dest.y + 2 && source.x == dest.x + 1)
                || (source.y == dest.y + 2 && source.x == dest.x - 1)
                || (source.y == dest.y - 2 && source.x == dest.x + 1)
                || (source.y == dest.y - 2 && source.x == dest.x - 1)
                || (source.y == dest.y + 1 && source.x == dest.x + 2)
                || (source.y == dest.y - 1 && source.x == dest.x + 2)
                || (source.y == dest.y + 1 && source.x == dest.x - 2)
                || (source.y == dest.y - 1 && source.x == dest.x - 2)) {
            steps = new Cell[] {dest };
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    public Cell[] moveAllWayOneStep(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        if ((source.y == dest.y + 1 && source.x == dest.x + 1)
                || (source.y == dest.y + 1 && source.x == dest.x - 1)
                || (source.y == dest.y - 1 && source.x == dest.x + 1)
                || (source.y == dest.y - 1 && source.x == dest.x - 1)
                || (source.y == dest.y + 1 && source.x == dest.x)
                || (source.y == dest.y - 1 && source.x == dest.x)
                || (source.y == dest.y && source.x == dest.x + 1)
                || (source.y == dest.y && source.x == dest.x - 1)) {
            steps = new Cell[] {dest};
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    public Cell[] godLikeMove(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps;
        int moveX = Math.abs(source.x - dest.x);
        int moveY = Math.abs(source.y - dest.y);
        if (moveX == moveY) {
            steps = moveDiagonal(source, dest);
        } else if (moveX == 0 || moveY == 0) {
            steps = moveForward(source, dest);
        } else {
            throw new ImpossibleMoveException();
        }
        return steps;
    }


    public abstract Figure copy(Cell dest);

}
