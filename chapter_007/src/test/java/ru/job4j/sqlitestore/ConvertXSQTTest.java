package ru.job4j.sqlitestore;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;

import static org.junit.Assert.*;

public class ConvertXSQTTest {

    /**
     * Не знаю, что тут сравнить. Если только заморачиваться и сравнивать два XML.
     */
    @Test
    public void name() {

        ConvertXSQT converter = new ConvertXSQT();
        try {
            converter.convert(
                    new File("storexml.xml"),
                    new File("newstorexml.xml"),
                    new File("scheme.xsl"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}