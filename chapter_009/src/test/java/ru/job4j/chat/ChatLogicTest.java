package ru.job4j.chat;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ChatLogicTest {


    @Test
    public void whenGetPrhaseFromFileThanTrue() throws IOException {
        ChatLogic logic = new ChatLogic();
        File input = File.createTempFile("txt", ".txt");
        input.deleteOnExit();
        try (FileWriter fw = new FileWriter(input)) {
            fw.write("first line\n");
            fw.write("second line\n");
        }
        String result;
        try (RandomAccessFile raf = new RandomAccessFile(input, "r")) {
                result = logic.getPhrase(raf);
            }
        String expectedOne = "first line";
        String expectedTwo = "second line";
        assertTrue(result.equals(expectedOne) || result.equals(expectedTwo));
    }
}