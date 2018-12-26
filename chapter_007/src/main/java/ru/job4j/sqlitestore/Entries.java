package ru.job4j.sqlitestore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class containing a list of database items for converting a database to XML.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.10.2018
 */
@XmlRootElement
public class Entries {
    public List<Entry> entry = new ArrayList<>();
}
