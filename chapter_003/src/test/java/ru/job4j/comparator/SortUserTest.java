package ru.job4j.comparator;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenAddFourThenSortedMap() {
        SortUser sort = new SortUser();
        List<User> users = List.of(
                new User("Roman", 33),
                new User("Ivan", 18)
        );
        Set<User> expected = Set.of(
                new User("Ivan", 18),
                new User("Roman", 33)
        );
        assertThat(sort.sort(users), is(expected));
    }

    @Test
    public void whenAddFourAndSortByNameLength() {
        SortUser sort = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User("Sergey", 55));
        users.add(new User("Olga", 30));
        List<User> expected = List.of(
                new User("Olga", 30),
                new User("Sergey", 55)
        );
        assertThat(sort.sortNameLength(users), is(expected));
    }

    @Test
    public void whenAddFourAndSortByNameLengthAndAge() {
        SortUser sort = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Roman", 33),
                        new User("Sergey", 55),
                        new User("Sergey", 30),
                        new User("Roman", 18)
                )
        );
        List<User> expected = List.of(
                new User("Roman", 18),
                new User("Roman", 33),
                new User("Sergey", 30),
                new User("Sergey", 55)
        );
        assertThat(sort.sortByAllFields(users), is(expected));
    }

}
