package ru.job4j.bomberman;

/**
 * BomberMan class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.09.2018
 */
public class BomberMan {
    private Cell position;

    public BomberMan(int x, int y) {
        this.position = new Cell(x, y);
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("Current Bomber location in %s", position);
    }
}
