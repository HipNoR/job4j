package ru.job4j.parsing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserTest {
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
    public void printTestOne() {
        Parser test = new Parser();
        test.parseString("a[b{c}d)e]");
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("String is invalid!")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void printTestTwo() {
        Parser test = new Parser();
        test.parseString("a[b{c(d)e}f]");
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Pair: (). Opens at position: 5. Closes at position: 7.")
                                .append(System.lineSeparator())
                                .append("Pair: {}. Opens at position: 3. Closes at position: 9.")
                                .append(System.lineSeparator())
                                .append("Pair: []. Opens at position: 1. Closes at position: 11.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void printTestThree() {
        Parser test = new Parser();
        test.parseString("Test for method[Is{it(really)(can)do}it]");
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Pair: (). Opens at position: 21. Closes at position: 28.")
                                .append(System.lineSeparator())
                                .append("Pair: (). Opens at position: 29. Closes at position: 33.")
                                .append(System.lineSeparator())
                                .append("Pair: {}. Opens at position: 18. Closes at position: 36.")
                                .append(System.lineSeparator())
                                .append("Pair: []. Opens at position: 15. Closes at position: 39.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
