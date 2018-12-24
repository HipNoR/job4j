package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerTest {

    public Connection init() {
        try (InputStream in = Tracker.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);

        }
    }

    @Test
    public void whenCreateNewItemThanTrue() throws SQLException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Item For Test", "desc"));
            assertThat(tracker.findByName("Item For Test").size(), is(1));
        }
    }

    @Test
    public void whenAddTwoItemsThanGetAllIsTwo() throws SQLException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            Item result = tracker.add(new Item("Item For Test", "desc"));
            assertThat(tracker.findById(result.getId()), is(result));
        }
    }

    @Test
    public void whenReplaceThanTrue() throws SQLException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            Item first = tracker.add(new Item("Item For Test", "desc"));
            Item replacer = new Item("replaced name", "descripted");
            tracker.replace(first.getId(), replacer);
            assertThat(tracker.findById(first.getId()).getName(), is(replacer.getName()));
        }
    }

    @Test
    public void whenAddAndDeleteThanTrue() throws SQLException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            Item result = tracker.add(new Item("Item For Test", "desc"));
            assertTrue(tracker.delete(result.getId()));
        }
    }

    @Test
    public void whenDeleteNotExistedThanFalse() throws SQLException {
        try (Tracker tracker = new Tracker(ConnectionRollback.create(this.init()))) {
            assertFalse(tracker.delete("000"));
        }
    }
}