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
        Pattern first = Pattern.compile("^([K]\\d+)[\\\\]([S][K]\\d+)");
        Pattern second = Pattern.compile("^([K]\\d+)[\\\\]([S][K]\\d+)[\\\\]([S][S][K]\\d+)$");
        char one;
        char two;
        String lvlOne;
        String lvlTwo;
        for (int index = 0; index < list.size(); index++) {
            one = list.get(index).charAt(1);
            lvlOne = String.format("K%s", one);
            if (second.matcher(list.get(index)).matches()) {
                two = list.get(index).charAt(5);
                lvlTwo = String.format("K%s\\SK%s", one, two);
                if (!list.contains(lvlOne)) {
                    list.add(lvlOne);
                }
                if (!list.contains(lvlTwo)) {
                    list.add(lvlTwo);
                }
            }
            if (first.matcher(list.get(index)).matches()) {
                if (!list.contains(lvlOne)) {
                    list.add(lvlOne);
                }
            }
        }
    }
}
