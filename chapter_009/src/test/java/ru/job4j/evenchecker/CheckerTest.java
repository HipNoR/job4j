package ru.job4j.evenchecker;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class CheckerTest {

    @Test
    public void whenInputEvenIntegerThanTrue() {
        Checker checker = new Checker();
        Integer data = 12;
        InputStream is = new ByteArrayInputStream(new byte[]{data.byteValue()});
        assertTrue(checker.isNumber(is));
    }

    @Test
    public void whenInputOddIntegerThanFalse() {
        Checker checker = new Checker();
        Integer data = 13;
        InputStream is = new ByteArrayInputStream(new byte[]{data.byteValue()});
        assertFalse(checker.isNumber(is));
    }

    @Test
    public void whenInputEvenCharThanTrue() {
        Checker checker = new Checker();
        String data = "n";
        InputStream is = new ByteArrayInputStream(data.getBytes());
        assertTrue(checker.isNumber(is));
    }

    @Test
    public void whenOddEvenCharThanTrue() {
        Checker checker = new Checker();
        String data = "o";
        InputStream is = new ByteArrayInputStream(data.getBytes());
        assertFalse(checker.isNumber(is));
    }
}