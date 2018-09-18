package ru.job4j.bomberman;

/**
 * Monster class.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 18.09.2018
 */
public class Monster {
    private Cell position;
    private int num;

    public Monster(Cell pos, int num) {
        this.position = pos;
        this.num = num;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return String.format("Current Monster#%s location is %s", num, position);
    }
}
