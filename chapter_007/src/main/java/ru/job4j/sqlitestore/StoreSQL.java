package ru.job4j.sqlitestore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.vacparser.DBWorker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class for creating a new SQLite database and inserting N items into it.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 09.10.2018
 */
public class StoreSQL implements AutoCloseable {

    private final Logger log = LogManager.getLogger(DBWorker.class);
    /**
     * Connection to SQLite database.
     */
    private Connection connect = null;

    /**
     * Creates a connection to the database.
     * @param config with connection parameters.
     */
    public void setConnection(File config) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(config));
        } catch (IOException e) {
            log.error("IOException", e);
        }
        String url = String.valueOf(prop.getProperty("CONNECT_TO_DB"));
        try {
            connect = DriverManager.getConnection(url);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    /**
     * Create a new Table in the database if it does not exists.
     * Each time creates a new table to remove old items.
     */
    public void createStructure() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS entry");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS entry ( field INTEGER)");
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }

    /**
     * Generates N items in a table from 1 to n.
     * @param n number of items.
     * @throws SQLException if exception.
     */
    public void generate(int n) throws SQLException {
        log.info("Start inserting {} items into database", n);
        try (Statement statement = connect.createStatement()) {
            for (int index = 1; index <= n; index++) {
                statement.executeUpdate(String.format("INSERT INTO entry VALUES (%s)", index));
            }
            connect.commit();
        } catch (SQLException e) {
            log.error("SQLException", e);
            connect.rollback();
        }
        log.info("{} elements inserted", n);
    }

    /**
     * List of all elements in the database.
     * @return list.
     */
    public List<Entry> selectData() {
        List<Entry> result = new ArrayList<>();
        try (Statement statement = connect.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                Entry entry = new Entry(rs.getInt("field"));
                result.add(entry);
            }
            rs.close();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
        return result;
    }

    @Override
    public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            log.error("SQLException", e);
        }
    }
}
