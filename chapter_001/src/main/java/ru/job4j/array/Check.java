package ru.job4j.array;

/**
 * Class Check verifies true or false in array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Check {
    /**
     * Method verifies true or false in array.
     * @param data input array.
     * @return - true or false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 1; index < data.length - 1; index++) {
            if (data[0] != data[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
