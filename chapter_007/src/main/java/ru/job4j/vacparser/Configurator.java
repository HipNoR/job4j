package ru.job4j.vacparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class allows you to get properties.
 * Gets from resources or path.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 19.10.2018
 */
public class Configurator {
    private Properties properties = new Properties();

    /**
     * Method get file from path and load Properties from this file.
     * @param path path to properties file.
     * @return Properties loaded from file.
     */
    public Properties pathProperties(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path))) {
            properties.load(fis);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Method get file from resources dir  or jar package and load Properties from this file.
     * @param name of properties file in jar package.
     * @return Properties loaded from file.
     */
    public Properties resProperties(String name) {
        try (InputStream fis = getClass().getClassLoader().getResourceAsStream(name)) {
        properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
