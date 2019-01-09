package ru.job4j.sort;

import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BigFileSorterTest {
    BigFileSorter sorter = new BigFileSorter();


    @Test
    public void whenSortBigFileThanMinStringIsFirst() throws IOException {
        File input = new File(BigFileSorter.class.getClassLoader().getResource("UnsortedText.txt").getPath());
        File output = File.createTempFile("tmp", ".txt");
        output.deleteOnExit();
        sorter.sort(input, output);
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
            result = reader.readLine();
        }
        System.out.println(result);
        String expected = "Как тренироваться";
        assertThat(result, is(expected));
    }

    @Test
    public void whenSplitTwoFilesThenSplittedByHalf() throws IOException {
        File input = File.createTempFile("tmp", ".txt");
        input.deleteOnExit();
        try (FileWriter writer = new FileWriter(input)) {
            writer.write("first line");
            writer.write("\n");
            writer.write("second line");
        }
        File[] files = sorter.splitFile(input);
        files[0].deleteOnExit();
        files[1].deleteOnExit();
        String leftFileLine;
        String rigthFileLine;
        try (BufferedReader leftReader = new BufferedReader(new FileReader(files[0]));
             BufferedReader rightReader = new BufferedReader(new FileReader(files[1]))
        ) {
            leftFileLine = leftReader.readLine();
            rigthFileLine = rightReader.readLine();
        }
        assertThat(leftFileLine, is("first line"));
        assertThat(rigthFileLine, is("second line"));
    }

    @Test
    public void whenSortFileThenFirstIsMinimum() throws IOException {
        File input = File.createTempFile("tmp", ".txt");
        input.deleteOnExit();
        try (FileWriter writer = new FileWriter(input)) {
            writer.write("middle by length line");
            writer.write("\n");
            writer.write("this is the longest line in this file");
            writer.write("\n");
            writer.write("short line");
        }
        File sorted = File.createTempFile("tmp", ".txt");
        sorted.deleteOnExit();
        sorter.sort(input, sorted);
        List<String> expected = List.of(
                "short line",
                "middle by length line",
                "this is the longest line in this file"
        );
        List<String> result;
        try (var reader = new BufferedReader(new FileReader(sorted))) {
            result = reader.lines().collect(Collectors.toList());
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenMergeTwoSortedFilesThanTrue() throws IOException {
        File leftInput = File.createTempFile("tmp", ".txt");
        leftInput.deleteOnExit();
        try (FileWriter writer = new FileWriter(leftInput)) {
            writer.write("left first line");
            writer.write("\n");
            writer.write("left second line plus plus");
            writer.write("\n");
        }
        File rigthInput = File.createTempFile("tmp", ".txt");
        rigthInput.deleteOnExit();
        try (FileWriter writer = new FileWriter(rigthInput)) {
            writer.write("right first line plus");
            writer.write("\n");
            writer.write("right second line plus plus plus");
            writer.write("\n");
        }
        File merged = File.createTempFile("tmp", ".txt");
        merged.deleteOnExit();
        sorter.mergeFiles(leftInput, rigthInput, merged);
        List<String> result;
        try (var reader = new BufferedReader(new FileReader(merged))) {
            result = reader.lines().collect(Collectors.toList());
        }
        List<String> expected = List.of(
                "left first line",
                "right first line plus",
                "left second line plus plus",
                "right second line plus plus plus"
        );
        assertThat(result, is(expected));
    }
}