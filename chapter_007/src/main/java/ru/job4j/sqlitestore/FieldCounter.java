package ru.job4j.sqlitestore;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The class that processes events when parsing the CML file.
 * Sum all field "field" values.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 11.10.2018
 */
public class FieldCounter extends DefaultHandler {
    private long sum = 0;

    public long getSum() {
        return sum;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Starting XML parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("XML parsing ended.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            sum += Integer.valueOf(attributes.getValue("field"));
        }
    }
}
