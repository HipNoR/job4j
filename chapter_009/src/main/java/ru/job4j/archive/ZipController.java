package ru.job4j.archive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Controller class for the program of archiving files in a zip archive.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.12.2018
 */
public class ZipController {
    private static final Logger LOG = LogManager.getLogger(ZipController.class.getName());

    /**
     * List of file extensions for archiving.
     */
    private List<String> extensions;

    /**
     * List of files to be archived.
     */
    private List<File> files = new ArrayList<>();

    /**
     * Path to directory.
     */
    private String path;

    /**
     * The zip file to be created.
     */
    private String targetZip;

    /**
     * Constructor with main parameters.
     * @param path directory for search.
     * @param extensions pattern.
     * @param targetZip target zip-archive.
     */
    public ZipController(String path, String extensions, String targetZip) {
        this.path = checkPath(path);
        this.targetZip = this.path + targetZip;
        setExtensions(extensions);
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public String getTargetZip() {
        return targetZip;
    }

    public List<File> getFiles() {
        return files;
    }

    /**
     * The method starts a search in the directory and archives the found files.
     */
    public void doZip() {
        findTargetFiles();
        zipFiles();
    }

    /**
     * The method splits the extension pattern into individual extensions and adds them to the list.
     * To each template adds "." at the beginning.
     * e.g "xml,java,txt" -> {".xml", ".java", ".txt"}.
     * @param template of extensions.
     */
    private void setExtensions(String template) {
        extensions = Arrays.stream(template.split(","))
                .map(s -> String.format(".%s", s))
                .collect(Collectors.toList());
    }

    /**
     * The method searches for files in the path directory.
     */
    public void findTargetFiles() {
        searchFiles(this.path);
    }

    /**
     * The method recursively searches for files in the directory
     * whose extensions are the same as those specified in the @extensions.
     * @param path directory for search.
     */
    public void searchFiles(String path) {
        var dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(file.getPath());
                } else {
                    if (checkTemplate(file.getName())) {
                        this.files.add(file);
                    }
                }
            }
        }
    }

    /**
     * The method archives all files from the list into a zip archive.
     */
    public void zipFiles() {
        File zip = new File(targetZip);
        try (var fos = new FileOutputStream(zip);
             var zout = new ZipOutputStream(fos)
        ) {
            files.forEach(file -> zipFile(file, zout));
            LOG.info("Total {} files added to archive.", files.size());
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * This method adds the file to the zip archive.
     * @param file will be added to the zip archive.
     * @param zout ZipOutputStream of zip file.
     */
    public void zipFile(File file, ZipOutputStream zout) {
        try (var fis = new FileInputStream(file)
        ) {
            var p = file.getPath().replace(path, "");
            var entry = new ZipEntry(p);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (FileNotFoundException e) {
            LOG.error("File not found ({})", file);
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * The method checks the file for compliance with extension templates.
     * @param name to check.
     * @return true if
     */
    public boolean checkTemplate(String name) {
        boolean valid = false;
        for (String temp : extensions) {
            if (name.contains(temp)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    /**
     * The method checks the directory separator at the end of the path string.
     * @param path to check.
     * @return source path or with an added separator at the end.
     */
    private String checkPath(String path) {
        int size = path.length();
        return path.charAt(size - 1) == File.separator.charAt(0) ? path : path + File.separator;
    }
}
