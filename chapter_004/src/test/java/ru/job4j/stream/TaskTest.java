package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TaskTest {
    List<Task> tasks;

    @Before
    public void prepareList() {
        tasks = Arrays.asList(
                new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100)
        );
    }

    @Test
    public void whenFilterByNameAndFind() {
        List<Task> bugs = tasks.stream()
                .filter(task -> task.getName().contains("Bug"))
                .collect(Collectors.toList());
        assertThat(bugs.size(), is(2));
    }

    @Test
    public void whenCreateListWithNames() {
        List<String> names = tasks.stream()
                .map(Task::getName)
                .collect(Collectors.toList());
        List<String> expected = Arrays.asList(
                "Bug #1", "Task #2", "Bug #3"
        );
        assertThat(names, is(expected));
    }

    @Test
    public void whenSumAllTimeSpentThanTrue() {
        long total = tasks.stream()
                .map(Task::getSpent)
                .reduce(0L, Long::sum);
        assertThat(total, is(300L));
    }
}