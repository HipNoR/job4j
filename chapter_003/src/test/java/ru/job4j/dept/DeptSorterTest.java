package ru.job4j.dept;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DeptSorterTest {
    @Test
    public void sortByNormal() {
        ArrayList<String> list = new ArrayList<>();
        DeptSorter sorter = new DeptSorter();
        list.add("K1\\SK1\\SSK1");
        list.add("K1\\SK1\\SSK2");
        list.add("K2");
        list.add("K1\\SK1");
        list.add("K2\\SK1\\SSK1");
        list.add("K1\\SK2");
        list.add("K2\\SK1\\SSK2");
        list.add("K1\\SK1\\SSK3\\SSSK1\\SSSSK1");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("K1");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK1\\SSK3");
        expected.add("K1\\SK1\\SSK3\\SSSK1");
        expected.add("K1\\SK1\\SSK3\\SSSK1\\SSSSK1");
        expected.add("K1\\SK2");
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K2\\SK1\\SSK2");
        sorter.sortNatural(list);
        assertThat(list, is(expected));
    }

    @Test
    public void sortByReverse() {
        ArrayList<String> list = new ArrayList<>();
        DeptSorter sorter = new DeptSorter();
        list.add("K1\\SK1\\SSK1");
        list.add("K1\\SK1\\SSK2");
        list.add("K2");
        list.add("K1\\SK1");
        list.add("K2\\SK1\\SSK1");
        list.add("K1\\SK2");
        list.add("K2\\SK1\\SSK2");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("K2");
        expected.add("K2\\SK1");
        expected.add("K2\\SK1\\SSK2");
        expected.add("K2\\SK1\\SSK1");
        expected.add("K1");
        expected.add("K1\\SK2");
        expected.add("K1\\SK1");
        expected.add("K1\\SK1\\SSK2");
        expected.add("K1\\SK1\\SSK1");
        sorter.sortReverse(list);
        assertThat(list, is(expected));
    }
}
