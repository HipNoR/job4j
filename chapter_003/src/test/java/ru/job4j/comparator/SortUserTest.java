package ru.job4j.comparator;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    // поле ссылки на стандартный вывод в консоль
    private final PrintStream stdout = System.out;
    // Поле - буфер для хранения данных вывода
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenAddFourThenSortedMap() {
        System.setOut(new PrintStream(this.out));
        SortUser sort = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Roman", 33),
                        new User("Ivan", 18),
                        new User("Sergey", 55),
                        new User("Olga", 30)
                )
        );
        Set<User> result = sort.sort(users);
        System.out.print(result);
        assertThat(out.toString(), is(
                new StringBuilder()
                        .append("[User { Age: 18. Name: Ivan.},")
                        .append(" User { Age: 30. Name: Olga.},")
                        .append(" User { Age: 33. Name: Roman.},")
                        .append(" User { Age: 55. Name: Sergey.}]")
                        .toString()
                )
        );
        System.setOut(this.stdout);
    }

    @Test
    public void whenAddFourAndSortByNameLength() {
        SortUser sort = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("Roman", 33),
                        new User("Ivan", 18),
                        new User("Sergey", 55),
                        new User("Olga", 30)
                )
        );
        sort.sortNameLength(users);
        assertThat(users.get(3).getName(), is("Sergey"));
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
        sort.sortByAllFields(users);
        assertThat(users.get(3).getAge(), is(55));
    }

    @Test
    public void whenAddFourAndSortByNameLengthAndAgeByShort() {
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
        sort.sortByAllFieldsShort(users);
        assertThat(users.get(3).getAge(), is(55));
    }
}
