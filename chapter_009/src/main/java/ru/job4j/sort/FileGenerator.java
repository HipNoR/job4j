package ru.job4j.sort;


import java.io.*;
import java.util.Random;

/**
 * Random string file generator.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.01.2019
 */
public class FileGenerator {

    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz012345678";
    private Random r = new Random();

    /**
     *
     * @param file target file to add lines.
     * @param lines number of lines in file.
     */
    public void getRandomText(File file, int lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))
        ) {
            for (int i = 0; i < lines; i++) {
                bw.write(randomString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method creates a random string of arbitrary length.
     * Maximum length = 600 characters.
     * @return generated string.
     */
    private String randomString() {
        int size = (int) (Math.random() * 600);
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < size; index++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET.length())));
        }
        sb.append("\n");
        return sb.toString();
    }
}
