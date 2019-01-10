package ru.job4j.archive;


/**
 * Config class for archiver program.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 10.01.2019
 */
public class Config {

    /**
     * Folder to search for target files.
     */
    private String folder;

    /**
     * File extensions to be added to the archive.
     */
    private String extensions;

    /**
     * Zip archive name.
     */
    private String zipName;


    public String getFolder() {
        return folder;
    }

    public String getExtensions() {
        return extensions;
    }

    public String getZipName() {
        return zipName;
    }

    /**
     * The method checks parameters and load them.
     * @param args array of parameters.
     * @return true if order is respected and loaded.
     */
    public boolean loadParameters(String[] args) {
        boolean result = checkOrder(args);
        if (result) {
            setParameters(args);
        }
        return result;
    }

    /**
     * Checks the order of the parameters.
     * The parameter type declaration must precede its value.
     * @param args array of parameters.
     * @return true if order is respected.
     */
    private boolean checkOrder(String[] args) {
        boolean result = true;
        for (int index = 0; index < args.length; index += 2) {
            if (!args[index].equals("-d") && !args[index].equals("-e") && !args[index].equals("-o")) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Loads parameters.
     * @param args array of parameters.
     */
    private void setParameters(String[] args) {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-d")) {
                folder = args[++index];
            }
            if (args[index].equals("-e")) {
                extensions = args[++index];
            }
            if (args[index].equals("-o")) {
                zipName = args[++index];
            }
        }
    }
}
