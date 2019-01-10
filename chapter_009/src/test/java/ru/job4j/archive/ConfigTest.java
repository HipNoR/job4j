package ru.job4j.archive;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenLoadValidParametersThanTrue() {
        Config cfg = new Config();
        String[] args = {
                "-d", "c:\\test",
                "-e", "java",
                "-o", "target.zip",
        };
        assertTrue(cfg.loadParameters(args));
    }

    @Test
    public void whenLoadParamsInNotValidOrderThanFalse() {
        Config cfg = new Config();
        String[] args = {
                "c:\\test", "-d",
                "-e", "java",
                "-o", "target.zip",
        };
        assertFalse(cfg.loadParameters(args));
    }

    @Test
    public void whenLoadParametersAndGetThemThenEquals() {
        Config cfg = new Config();
        String[] args = {
                "-d", "c:\\test",
                "-e", "java",
                "-o", "target.zip",
        };
        cfg.loadParameters(args);
        List<String> result = List.of(cfg.getFolder(), cfg.getExtensions(), cfg.getZipName());
        List<String> expected = List.of("c:\\test", "java", "target.zip");
        assertThat(result, is(expected));
    }
}