package ru.job4j.parsing;

/**
 * Class bracket.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
class Bracket {
    private char name;
    private int open;
    private int close;

    Bracket(char name, int open, int close) {
        this.name = name;
        this.open = open;
        this.close = close;
    }

    public char getName() {
        return name;
    }

    public int getOpen() {
        return open;
    }

    public int getClose() {
        return close;
    }
}
