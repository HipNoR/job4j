package ru.job4j.evenchecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * The class contains a method for checking the byte stream.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 27.12.2018
 */
public class Checker {
    private static final Logger LOG = LogManager.getLogger(Checker.class.getName());


    /**
     * This method checks if the number in the byte stream is even.
     * @param in input stream.
     * @return number is even ? true : false.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (var input = in; var bis = new BufferedInputStream(input)) {
            var num = bis.read();
            if (num % 2 == 0) {
                result = true;
                LOG.info("{} is even number.", num);
            } else {
                LOG.info("{} is odd number.", num);
            }
        } catch (IOException e) {
            LOG.error("Exception", e);
        }
        return result;
    }
}
