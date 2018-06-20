package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.exceptions.*;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Logic for chess game.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException,
            FigureNotFoundException {
        boolean rst = false;
        Optional<Integer> index = findBy(source);
        Optional<Integer> target = findBy(dest);
        if (!index.isPresent()) {                   // это работает в тестах
            throw new FigureNotFoundException();
        }
        Cell[] steps = this.figures[index.get()].way(source, dest);
        for (int step = 0; step < steps.length; step++) {
            Optional<Integer> empty = findBy(steps[step]);
            if (empty.isPresent() || target.isPresent()) {
                throw new OccupiedWayException();
            }
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index.get()] = this.figures[index.get()].copy(dest);
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private Optional<Integer> findBy(Cell cell) {
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                return Optional.of(index);
            }
        }
        return Optional.empty();
    }
}
