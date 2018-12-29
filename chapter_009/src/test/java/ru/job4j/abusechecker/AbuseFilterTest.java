package ru.job4j.abusechecker;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbuseFilterTest {

    @Test
    public void whenAddInputStreamOfStringAndFilterAbuseThanFiltered() {
        String[] abuse = {"TEST", "bad"};
        AbuseFilter filter = new AbuseFilter();
        String input = "TEST bad string for testing bad";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbuses(in, out, abuse);
        String expected = "string for testing "; //в конце всегда прилипает пробел
        assertThat(out.toString(), is(expected));
    }

}