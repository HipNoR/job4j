package ru.job4j.pseudo;

import org.junit.Test;

import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class PaintTest {
    // поле ссылки на стандартный вывод в консоль
    private final PrintStream stdout = System.out;
    // Поле - буфер для хранения данных вывода
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    // Метод реализует замену стандартного вывода в консоль на вывод в память.
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }
    //Метод реализует обратный выход в консоль
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawSquare() {
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Square());
        // проверяем результат вычисления
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+ + + +")
                                .append(System.lineSeparator())
                                .append("+     +")
                                .append(System.lineSeparator())
                                .append("+     +")
                                .append(System.lineSeparator())
                                .append("+ + + +")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +   ")
                                .append(System.lineSeparator())
                                .append("  + +  ")
                                .append(System.lineSeparator())
                                .append(" + + + ")
                                .append(System.lineSeparator())
                                .append("+ + + +")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}