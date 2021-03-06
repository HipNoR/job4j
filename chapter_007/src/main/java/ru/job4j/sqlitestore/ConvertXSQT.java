package ru.job4j.sqlitestore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.vacparser.DBWorker;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * The class converts a XML document to a XML document of a different structure.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 10.10.2018
 */
public class ConvertXSQT {
    private final Logger log = LogManager.getLogger(DBWorker.class);

    /**
     * Method converts a XML document to a XML document of a different structure.
     * @param source to be converted.
     * @param dest converted file.
     * @param scheme transformation template.
     * @throws TransformerException if exception.
     */
    public void convert(File source, File dest, File scheme) throws TransformerException {
        log.info("The conversion of one XML to another started.");
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new StreamSource(source), new StreamResult(dest));
        log.info("The conversion of one XML to another ended.");
    }
}
