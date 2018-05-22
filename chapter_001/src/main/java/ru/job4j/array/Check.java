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
        boolean result = false;
        for (int index = 0; index < data.length - 1; index++) {
            if (data[index] == data[index + 1]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}
