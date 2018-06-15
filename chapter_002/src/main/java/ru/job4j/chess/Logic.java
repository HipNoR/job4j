package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.exceptions.*;

import java.util.Optional;

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
        int index = this.findBy(source);
        int target = this.findBy(dest);
        try {
            if (index == -1) {                   // реализация не работает в тестах
                throw new FigureNotFoundException();
            }
            Cell[] steps = this.figures[index].way(source, dest);
            for (int step = 0; step < steps.length; step++) {
                int empty = findBy(steps[step]);
                if (empty != -1 || target != -1) {
                    throw new OccupiedWayException();
                }
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        } catch (FigureNotFoundException fnfe) {
            System.out.println("Empty cell!");
        } catch (ImpossibleMoveException ime) {
            System.out.println("Impossible move!");
        } catch (OccupiedWayException owe) {
            System.out.println("The way is busy!");
        }

        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
    /*
    private boolean freeWay(Cell position, Cell dest) {
        boolean free = true;

        return  free;
    }
    */
}
