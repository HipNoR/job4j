package ru.job4j.vacparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class allows you to get properties.
 * Gets from resources or path.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 19.10.2018
 */
public class Configurator {
    /**
     * Logger for info output.
     */
    private static final Logger LOGGER = LogManager.getLogger(DBWorker.class);

    /**
     * Method get file from resources dir  or jar package and load Properties from this file.
     * @param name of properties file in jar package.
     * @return Properties loaded from file.
     */
    public static Properties getProperties(String name) {
        Properties properties = new Properties();
        try (InputStream fis = Configurator.class.getClassLoader().getResourceAsStream(name)) {
        properties.load(fis);
        } catch (IOException e) {
            LOGGER.error("ERROR", e);
        }
        return properties;
    }
}
