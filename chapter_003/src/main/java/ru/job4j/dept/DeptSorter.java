package ru.job4j.dept;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.*;

/**
 * Class for sorting the list of departments.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class DeptSorter {

    /**
     * Method sorts the list in lexicographical order.
     * Before sorts call private method checker.
     *
     * @param list to sorts.
     */
    public void sortNatural(ArrayList<String> list) {
        checker(list);
        list.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
    }

    /**
     * Method sorts the list in lexicographical order.
     * But a department with a smaller size of name is upper one.
     * Before sorts call private method checker.
     *
     * @param list to sort.
     */
    public void sortReverse(ArrayList<String> list) {
        checker(list);
        list.sort(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int result;
                        if (o1.length() == o2.length()) {
                            result = o2.compareTo(o1);
                        } else {
                            int size = Math.min(o1.length(), o2.length());
                            String first = o1.substring(0, size);
                            String second = o2.substring(0, size);
                            if (first.compareTo(second) == 0 && o1.length() > o2.length()) {
                                result = 1;
                            } else if (first.compareTo(second) == 0 && o1.length() < o2.length()) {
                                result = -1;
                            } else {
                                result = o2.compareTo(o1);
                            }
                        }
                        return result;
                    }
                }
        );
    }

    /**
     * Checks the list for items.
     * If list contain dept like @K1\SK1\SSK1@ then it must content dept's: @K1\SK1@
     * and @K1@. If not, add them.
     * @param list to check.
     */
    private void checker(ArrayList<String> list) {
        for (int out = 0; out < list.size(); out++) {
            String[] separated = list.get(out).split("\\\\");
            if (separated.length > 1) {
                String temp = separated[0];
                for (int in = 1; in < separated.length; in++) {
                    if (!list.contains(temp)) {
                        list.add(temp);
                    }
                    temp += "\\" + separated[in];
                }
            }
        }
    }
}