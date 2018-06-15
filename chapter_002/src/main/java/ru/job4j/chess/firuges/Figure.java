package ru.job4j.chess.firuges;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

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

    public Cell[] movePawn(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        if (source.y == dest.y + 1 && source.x == dest.x) {
            steps = new Cell[] {dest };
            valid = true;
        }
        if (!valid) {
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    public Cell[] moveKnight(Cell source, Cell dest) throws ImpossibleMoveException {
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

    public Cell[] moveKing(Cell source, Cell dest) throws ImpossibleMoveException {
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

    public abstract Figure copy(Cell dest);

}
