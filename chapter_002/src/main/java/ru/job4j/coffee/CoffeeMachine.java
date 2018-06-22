package ru.job4j.coffee;

/**
 * Coffee machine class.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class CoffeeMachine {

    /**
     * Make money, choose coffee - take coffee and change.
     * @param value amount of money deposited.
     * @param price coffee price.
     * @return change in coins.
     */
    public int[] changes(int value, int price) {
        int change = (value > price ? value - price : 0);
        int count = 0;
        int[] result = new int[change];
        int[] values = {10, 5, 2, 1};
        for (int index = 0; index < values.length; index++) {
            while (change >= values[index]) {
                change -= values[index];
                result[count++] = values[index];
            }
        }
        int[] coins = new int[count];
        System.arraycopy(result, 0, coins, 0, count);
        return coins;
    }

}
