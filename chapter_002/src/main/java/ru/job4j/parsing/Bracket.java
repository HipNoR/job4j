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

    Bracket(char name, int open) {
        this.name = name;
        this.open = open;
    }

    public char getName() {
        return name;
    }

    public int getOpen() {
        return open;
    }
}
