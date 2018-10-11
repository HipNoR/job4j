package ru.job4j.sqlitestore;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {
    @Test
    public void name() {
        StoreSQL store = new StoreSQL();
        store.setConnection(new File("config.ini"));
        store.createStructure();
        try {
            store.generate(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Entry> entry = store.selectData();
        StoreXML storeXML = new StoreXML(new File("storexml.xml"));
        storeXML.save(entry);
        store.close();

        Entries result = new Entries();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
             result = (Entries) jaxbUnmarshaller.unmarshal(new File("storexml.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List<Entry> expected = new ArrayList<>();
        for (int index = 1; index <= 10; index++) {
            Entry toAdd = new Entry();
            toAdd.setField(index);
            expected.add(toAdd);
        }
        assertThat(result.entry, is(expected));
    }
}