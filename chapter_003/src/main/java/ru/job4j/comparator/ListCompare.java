package ru.job4j.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int size = left.length();
        if (left.length() < right.length()) {
            size = right.length();
        }
        char[] first = toCharArray(size, left);
        char[] second = toCharArray(size, right);
        int result = 0;
        for (int index = 0; index < size; index++) {
            if (Character.compare(first[index], second[index]) < 0) {
                result = -1;
                break;
            } else if (Character.compare(first[index], second[index]) > 0) {
                result = 1;
                break;
            }
        }
        return result;
    }

    private char[] toCharArray(int size, String string) {
        char[] values = new char[size];
        for (int index = 0; index < string.length(); index++) {
            values[index] = string.charAt(index);
        }
        return values;
    }
}