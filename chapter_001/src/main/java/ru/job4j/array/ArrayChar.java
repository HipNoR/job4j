package ru.job4j.array;

/**
 * Class ArrayChar - checks the prefix of the word.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Method checks the prefix of the word.
     * @param prefix prefix of the word.
     * @return true or false.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        char[] trimmedData = new char[value.length];
        for (int index = 0; index < trimmedData.length; index++) {
            trimmedData[index] = data[index];
        }
        for (int index = 0; index < trimmedData.length; index++) {
            if (trimmedData[index] != value[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
