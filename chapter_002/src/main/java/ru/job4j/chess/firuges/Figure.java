package ru.job4j.chess.firuges;

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

    public abstract Figure copy(Cell dest);

}
