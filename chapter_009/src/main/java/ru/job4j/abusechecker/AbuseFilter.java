package ru.job4j.abusechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class stream filter character.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 29.12.2018
 */
public class AbuseFilter {
    private static final Logger LOG = LogManager.getLogger(AbuseFilter.class.getName());


    /**
     * The method filters the input stream according to the words specified in the list of invalid words.
     * Words not deleted are sent to the output stream.
     * Important! A space is added to the end of each sent word.
     * @param in input stream.
     * @param out output stream.
     * @param abuse array of filterable words.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        var abused = List.of(abuse);
        boolean available = true;
        StringBuilder sb = new StringBuilder();
        try (var input = in; var bis = new BufferedInputStream(input)) {
            char c;
            while (available) {
                if (bis.available() == 0)  {
                    available = false;
                }
                c = (char) bis.read();
                if (c == ' ' || !available) {
                    var word = sb.toString();
                    if (!abused.contains(word)) {
                        LOG.info("Word \"{}\" passed the filter and passed to the output stream.", word);
                        word += " ";
                        out.write(word.getBytes());
                    } else {
                        LOG.info("Word \"{}\" has not passed the filter and deleted.", word);
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
        } catch (IOException e) {
            LOG.error("Error", e);
        }
    }
}
