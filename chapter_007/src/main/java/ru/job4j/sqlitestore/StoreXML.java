package ru.job4j.sqlitestore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * The class converts the List of SQLite database items to XML.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.10.2018
 */
public class StoreXML {
    /**
     * File to save.
     */
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * The method saves the list of database elements to XML.
     * @param list of items to convert.
     */
    public void save(List<Entry> list) {
        System.out.println("Database conversion to XML started.");
        Entries ent = new Entries();
        ent.entry = list;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class, Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(ent, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("Database conversion to XML ended.");
    }
}
