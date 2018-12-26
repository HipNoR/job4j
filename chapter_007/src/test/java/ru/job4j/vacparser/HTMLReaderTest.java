package ru.job4j.vacparser;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class HTMLReaderTest {

    @Test
    public void whenSearchForLastMonthThanNotNull() {
        HTMLReader reader = new HTMLReader(Configurator.getProperties("app.properties"),
                LocalDate.now().minusMonths(1L));
        assertTrue(reader.vacSearch().size() > 0);
    }
}
