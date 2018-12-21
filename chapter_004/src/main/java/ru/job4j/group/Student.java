package ru.job4j.group;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Student class.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 21.12.2018
 */
public class Student implements Comparable<Student> {
    private String name;
    private Integer scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    /**
     * This method selects students whose score is higher than the target.
     * @param students list of students.
     * @param bound of scope to filter the list.
     * @return list of students satisfying conditions.
     */
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted()
                .takeWhile(student -> student.getScope() > bound)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', scope='%s'}", name, scope);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scope.equals(student.scope) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }


    @Override
    public int compareTo(Student o) {
        return o.scope.compareTo(this.scope);
    }
}
