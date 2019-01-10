package ru.job4j.archive;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ZipControllerTest {

    @Test
    public void whenSetExtensionsPatternThanReturnListOfExtensions() throws IllegalAccessException, NoSuchFieldException {
        ZipController zip = new ZipController("test", "txt,java,xml", "test.zip");
        Field targetZip = zip.getClass().getDeclaredField("extensions");
        targetZip.setAccessible(true);
        Object value = targetZip.get(zip);
        List<String> expected = List.of(".txt", ".java", ".xml");
        assertThat(value, is(expected));

    }

    @Test
    public void whenSetPathAndZipFileThanProperPath() throws NoSuchFieldException, IllegalAccessException {
        String sep = File.separator;
        ZipController zip = new ZipController(
                String.format("test dir%1$stest dir 2%1$s", sep), "txt,java,xml", "test.zip");
        String expected = String.format("test dir%1$stest dir 2%1$stest.zip", sep);
        Field targetZip = zip.getClass().getDeclaredField("targetZip");
        targetZip.setAccessible(true);
        Object value = targetZip.get(zip);
        assertThat(value, is(expected));
    }

    @Test
    public void whenCreateTwoFilesAndSearchByExtensionTemplateThanTrue()
            throws IOException, NoSuchFieldException, IllegalAccessException {
        String testPath = System.getProperty("user.dir") + File.separator;
        List<File> files = List.of(
                new File(testPath + "first.ziptest"),
                new File(testPath + "testpack.zip")
        );
        for (File file : files) {
            file.createNewFile();
            file.deleteOnExit();
        }
        ZipController zip = new ZipController(testPath, "ziptest", files.get(1).getPath());
        zip.findTargetFiles();
        Field targetZip = zip.getClass().getDeclaredField("files");
        targetZip.setAccessible(true);
        Object value = targetZip.get(zip);
        List<File> expected = List.of(files.get(0));
        assertThat(value, is(expected));
    }

    @Test
    public void whenZipFileAndUnzipItThanTrue() throws IOException {
        String testPath = System.getProperty("user.dir") + File.separator;
        List<File> files = List.of(
                new File(testPath + "first.ziptest"),
                new File(testPath + "testpack.zip")
        );
        for (File file : files) {
            file.createNewFile();
            file.deleteOnExit();
        }
        try (Writer out = new FileWriter(testPath + "first.ziptest")) {
            out.write("String for test");
            out.flush();
        }
        ZipController zip = new ZipController(testPath, "ziptest", files.get(1).getName());
        zip.doZip();
        try (ZipFile zipFile = new ZipFile(testPath + "testpack.zip")
        ) {
            ZipEntry entry = zipFile.getEntry("first.ziptest");
            try (InputStream is = zipFile.getInputStream(entry)) {
                Files.copy(is, Paths.get(testPath + "firstunpacked.ziptest"));
            }
        }
        File file = new File(testPath + "firstunpacked.ziptest");
        file.deleteOnExit();
        assertTrue(FileUtils.contentEquals(files.get(0), file));
    }
}