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

    public Cell[] moveInline(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        int moveX = Math.abs(source.x - dest.x);
        int moveY = Math.abs(source.y - dest.y);
        int size = Math.max(moveX, moveY);
        if (source.y == dest.y + moveY && source.x == dest.x
                || source.y == dest.y - moveY && source.x == dest.x
                || source.y == dest.y && source.x == dest.x + moveX
                || source.y == dest.y && source.x == dest.x - moveX) {
            steps = new Cell[size];
            steps[0] = Cell.findCell(source.x + deltaX, source.y + deltaY);
            for (int index = 1; index < steps.length; index++) {
                steps[index] = Cell.findCell(steps[index - 1].x + deltaX, steps[index - 1].y + deltaY);
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
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        int move = Math.abs(source.x - dest.x);
        if (source.y == dest.y + move && source.x == dest.x + move
                || source.y == dest.y + move && source.x == dest.x - move
                || source.y == dest.y - move && source.x == dest.x + move
                || source.y == dest.y - move && source.x == dest.x - move) {
            steps = new Cell[move];
            steps[0] = Cell.findCell(source.x + deltaX, source.y + deltaY);
            for (int index = 1; index < steps.length; index++) {
                steps[index] = Cell.findCell(steps[index - 1].x + deltaX, steps[index - 1].y + deltaY);
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
            steps = moveInline(source, dest);
        } else {
            throw new ImpossibleMoveException();
        }
        return steps;
    }


    public abstract Figure copy(Cell dest);

}
