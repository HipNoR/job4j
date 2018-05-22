package ru.job4j.array;

/**
 * Class FinLoop search in array.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class FindLoop {
    /**
     * Method search the a value and returns the index of this value.
     * If there are no matches, then -1 is returned.
     * @param data
     * @param el
     * @return
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index != data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
