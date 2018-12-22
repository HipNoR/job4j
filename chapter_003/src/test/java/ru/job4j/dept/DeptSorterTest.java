package ru.job4j.dept;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> expected = List.of(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK3",
                "K1\\SK1\\SSK3\\SSSK1",
                "K1\\SK1\\SSK3\\SSSK1\\SSSSK1",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
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
        List<String> expected = List.of(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1");
        sorter.sortReverse(list);
        assertThat(list, is(expected));
    }
}
