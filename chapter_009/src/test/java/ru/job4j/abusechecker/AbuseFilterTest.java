package ru.job4j.abusechecker;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbuseFilterTest {
    AbuseFilter filter = new AbuseFilter();

    @Test
    public void whenAddInputStreamOfStringAndFilterAbuseThanFiltered() {
        String[] abuse = {"TEST", "bad"};
        String input = "TEST    bad string for testing bad\n"
                + "Second bad string";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbuses(in, out, abuse);
        String expected = "string for testing\n"
                + "Second string\n";
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAddInputStreamOfStringAndFilterAbuseThanFilteredSecondMethod() throws IOException {
        String[] abuse = {"TEST", "bad"};
        String input = "TEST    bad string for testing bad\n"
                + "Second bad string";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbusesSecond(in, out, abuse);
        String expected = "string for testing\n"
                + "Second string";
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAddInputStreamOfStringAndFilterAbuseThanFilterUniversalMethod() throws IOException {
        String[] abuse = {"TEST", "bad"};
        String input = "TEST    bad string for testing bad\n"
                + "Second bad string";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        filter.dropAbuseUniversal(in, out, abuse);
        String expected = "string for testing\n"
                + "Second string\n";
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenFilterFileByUniversalMethodThanTrue() throws IOException {
        String[] abuse = {"test", "bad"};
        List<String> source = List.of(
                "filter test file",
                "filter will remove bad words",
                "bad words will disappear"
        );
        File input = File.createTempFile("tmp", ".txt");
        input.deleteOnExit();
        File output = File.createTempFile("tmp", ".txt");
        output.deleteOnExit();
        try (FileWriter fw = new FileWriter(input)) {
            for (String s : source) {
                fw.write(s + "\n");
            }
        }
        try (FileInputStream in = new FileInputStream(input);
             FileOutputStream out = new FileOutputStream(output)
        ) {
            filter.dropAbuseUniversal(in, out, abuse);
        }
        List<String> result = new ArrayList<>();
        try (FileReader fr = new FileReader(output);
        BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        List<String> expected = List.of(
                "filter file",
                "filter will remove words",
                "words will disappear"
        );
        assertThat(result, is(expected));
    }
}