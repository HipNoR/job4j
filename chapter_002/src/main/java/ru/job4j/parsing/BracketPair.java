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

    @Override
    public String toString() {
        return String.format("Pair: %s. Opens at position: %s. Closes at position: %s.",
                this.name, this.open, this.close);
    }

    @Override
    public boolean equals(Object obj) {
        boolean valid = false;
        if (obj == this) {
            valid = true;
        }
        if (!valid && obj != null && obj.getClass() == getClass()) {
            BracketPair o = (BracketPair) obj;
            if (name.equals(o.name) && open == o.open && close == o.close) {
                valid = true;
            }
        }
        return valid;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
