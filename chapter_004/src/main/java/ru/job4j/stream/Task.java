package ru.job4j.stream;


import java.util.Objects;

/**
 * Simple class for stream example.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 21.12.2018
 */
public class Task {
    private final String name;
    private final long spent;

    public Task(String name, long spent) {
        this.name = name;
        this.spent = spent;
    }

    public String getName() {
        return name;
    }

    public long getSpent() {
        return spent;
    }

    @Override
    public String toString() {
        return String.format("Task{name='%s', spent='%s'}", name, spent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return spent == task.spent && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, spent);
    }
}
