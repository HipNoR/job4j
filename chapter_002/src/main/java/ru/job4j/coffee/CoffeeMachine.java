package ru.job4j.coffee;

public class CoffeeMachine {
    private int index = 0;
    private int one = 0;
    private int two = 0;
    private int five = 0;
    private int ten = 0;

    public int[] changes(int value, int price) {
        int change = value - price;
        while (change >= 10) {
            change = change - 10;
            ten++;
        }
        while (change >= 5) {
            change = change - 5;
            five++;
        }
        while (change >= 2) {
            change = change - 2;
            two++;
        }
        while (change >= 1) {
            change = change - 1;
            one++;
        }
        int[] changed = new int[one + two + five + ten];
        for (int index = 0; index < ten; index++) {
            changed[this.index] = 10;
            this.index++;
        }
        for (int index = 0; index < five; index++) {
            changed[this.index] = 5;
            this.index++;
        }
        for (int index = 0; index < two; index++) {
            changed[this.index] = 2;
            this.index++;
        }
        for (int index = 0; index < one; index++) {
            changed[this.index] = 1;
            this.index++;
        }
        return changed;
    }

}
