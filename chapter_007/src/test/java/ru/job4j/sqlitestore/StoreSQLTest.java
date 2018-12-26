package ru.job4j.sqlitestore;

import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenAddTenElementsAndSelectThanTrue() {
        StoreSQL store = new StoreSQL();
        store.setConnection(new File("config.ini"));
        store.createStructure();
        try {
            store.generate(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Entry> result = store.selectData();
        store.close();
        List<Entry> expected = Stream.iterate(1, n -> n + 1).limit(10).map(Entry::new).collect(Collectors.toList());
        assertThat(result, is(expected));
    }
}