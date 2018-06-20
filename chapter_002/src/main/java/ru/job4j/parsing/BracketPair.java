package ru.job4j.parsing;

/**
 * Class for pairs of brackets.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
class BracketPair {
    private String name;
    private int open;
    private int close;

    BracketPair(String name, int open, int close) {
        this.name = name;
        this.open = open;
        this.close = close;
    }

    public String getName() {
        return name;
    }

    public int getOpen() {
        return open;
    }

    public int getClose() {
        return close;
    }
}
