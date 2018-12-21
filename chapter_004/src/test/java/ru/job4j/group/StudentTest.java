package ru.job4j.group;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void whenAddThreeThanReturnTwo() {
        List<Student> group = List.of(
                new Student("test1", 8),
                new Student("test2", 3),
                new Student("test3", 10)
        );
        List<Student> expected = List.of(
                new Student("test3", 10),
                new Student("test1", 8)
        );
        assertThat(Student.levelOf(group, 5), is(expected));
    }

    @Test
    public void whenAddNullThanNullNotInResult() {
        List<Student> group = new ArrayList<>();
        group.add(new Student("test2", 3));
        group.add(new Student("test3", 10));
        group.add(null);
        List<Student> expected = List.of(
                new Student("test3", 10)
        );
        assertThat(Student.levelOf(group, 5), is(expected));
    }
}