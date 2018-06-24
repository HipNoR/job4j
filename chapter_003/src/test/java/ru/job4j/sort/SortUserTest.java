package ru.job4j.sort;

import org.junit.After;
import org.junit.Before;
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
    // Метод реализует замену стандартного вывода в консоль на вывод в память.
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    //Метод реализует обратный выход в консоль
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenAddFourThenSortedMap() {
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
    }
}
