package ru.job4j.archive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


/**
 * The main class for the program of archiving files in a zip archive. <br>
 * To use the program enter: <br>
 * java -jar pack.jar -d [dir] -e [pattern] -o [target] <br>
 * Where: <br>
 * [dir] directory to be scanned. All files with an extension matching the pattern will be added to the archive. <br>
 *     You must use double backslash "\\" in path string in Windows.<br>
 *         You must use slash "/" in path string in Unix.<br>
 * [pattern] Enumeration of file extensions through ",", without spaces, e.g. "java,xml,txt". <br>
 * [target] Zip archive name. A new file will be created. e.g. "test.zip". <br>
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.12.2018
 */
public class ZipArchiver {
    private static final Logger LOG = LogManager.getLogger(ZipArchiver.class.getName());

    public static void main(String[] args) {
        final var rec = "Example: {java -jar pack.jar -d first dir\\\\target dir\\\\ -e xml,java -o target.zip}";
        if (args.length < 6) {
            LOG.error("You have not entered enough parameters");
            LOG.info(rec);
        } else if (args.length > 6) {
            LOG.error("Extra parameters entered");
            LOG.info(rec);
        } else {
            var zip = new ZipController(args[1], args[3], args[5]);
            zip.doZip();
        }
    }
}
