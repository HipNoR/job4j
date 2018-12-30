package ru.job4j.sort;

import java.io.File;
import java.io.IOException;

/**
 * Sorter interface.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 29.12.2018
 */
public interface Sorter {
    void sort(File source, File dest) throws IOException;
}
