package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class StoreTest {
    List<Store.User> first;
    List<Store.User> second;

    @Before
    public void prepareForTests() {
        first = new ArrayList<>();
        first.add(new Store.User(0, "zero"));
        first.add(new Store.User(1, "one"));
        first.add(new Store.User(2, "two"));
        first.add(new Store.User(3, "three"));
        first.add(new Store.User(4, "four"));
        first.add(new Store.User(5, "five"));
        second = new ArrayList<>();
        second.addAll(first);
    }

    @Test
    public void whenDeleteOneThen() {
      second.remove(1);
      String result = Store.diff(first, second);
      assertThat(result, is("Added: 0. Changed: 0. Deleted: 1."));
    }

    @Test
    public void whenAddTwoThen() {
        second.add(new Store.User(6, "six"));
        second.add(new Store.User(7, "seven"));
        String result = Store.diff(first, second);
        assertThat(result, is("Added: 2. Changed: 0. Deleted: 0."));
    }

    @Test
    public void whenChangeOneThen() {
        second.set(2, new Store.User(2, "moddedTwo"));
        String result = Store.diff(first, second);
        assertThat(result, is("Added: 0. Changed: 1. Deleted: 0."));
    }

    @Test
    public void whenMultipleChangesThen() {
        second.set(2, new Store.User(2, "moddedTwo"));
        second.add(new Store.User(6, "six"));
        second.remove(1);
        second.set(3, new Store.User(4, "moddedFour"));
        String result = Store.diff(first, second);
        assertThat(result, is("Added: 1. Changed: 2. Deleted: 1."));
    }

    @Test
    public void linkedListTests() {
        LinkedList<Store.User> linkFirst = new LinkedList<>();
        linkFirst.add(new Store.User(0, "zero"));
        linkFirst.add(new Store.User(1, "one"));
        linkFirst.add(new Store.User(2, "two"));
        linkFirst.add(new Store.User(3, "three"));
        linkFirst.add(new Store.User(4, "four"));
        linkFirst.add(new Store.User(5, "five"));
        LinkedList<Store.User> linkSecond = new LinkedList<>();
        linkSecond.addAll(linkFirst);
        linkSecond.set(2, new Store.User(2, "moddedTwo"));
        linkSecond.add(new Store.User(6, "six"));
        linkSecond.remove(1);
        linkSecond.set(3, new Store.User(4, "moddedFour"));
        String result = Store.diff(linkFirst, linkSecond);
        assertThat(result, is("Added: 1. Changed: 2. Deleted: 1."));
    }
}