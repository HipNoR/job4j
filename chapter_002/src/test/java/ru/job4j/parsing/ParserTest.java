package ru.job4j.parsing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserTest {

    @Test (expected = NotValidException.class)
    public void printTestOne() {
        Parser test = new Parser();
        test.parseString("a[b{c}d)e]");
    }

    @Test
    public void printTestTwo() {
        Parser test = new Parser();
        List<BracketPair> result = test.parseString("a[b{c(d)e}f]");
        List<BracketPair> expected = new ArrayList<>();
        expected.add(new BracketPair("()", 5, 7));
        expected.add(new BracketPair("{}", 3, 9));
        expected.add(new BracketPair("[]", 1, 11));
        assertThat(result, is(expected));

    }

    @Test
    public void printTestThree() {
        Parser test = new Parser();
        List<BracketPair> result = test.parseString("Test for method[Is{it(really)(can)do}it]");
        List<BracketPair> expected = new ArrayList<>();
        expected.add(new BracketPair("()", 21, 28));
        expected.add(new BracketPair("()", 29, 33));
        expected.add(new BracketPair("{}", 18, 36));
        expected.add(new BracketPair("[]", 15, 39));
        assertThat(result, is(expected));

    }
}
