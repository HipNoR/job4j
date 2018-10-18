package ru.job4j.sqlitestore;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * SAX parser class.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 11.10.2018
 */
public class SumSAXParser {
    private long sum;

    public void parseSum(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xmlReader = sp.getXMLReader();
        FieldCounter counter = new FieldCounter();
        xmlReader.setContentHandler(counter);
        xmlReader.parse(file.getAbsolutePath());
        sum = counter.getSum();
        System.out.println(String.format("Sum of all field's is %s", sum));
    }

    public long getSum() {
        return sum;
    }
}
