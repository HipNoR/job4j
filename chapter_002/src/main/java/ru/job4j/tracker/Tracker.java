package ru.job4j.tracker;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class Tracker repository of requests.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.3
 */
public class Tracker implements AutoCloseable {

    private  Properties properties = new Properties();

    private Connection connection;


    public Tracker() {
        getProperties("tracker.properties");
        getConnection();
        structureCheck();
    }

    public Tracker(Connection connection) {
        getProperties("tracker.properties");
        this.connection = connection;
    }

    /**
     * Add item to List.
     * @param item input object of class item.
     */
    public Item add(Item item) {
        try (final PreparedStatement st = this.connection.prepareStatement(
                "INSERT INTO tasks (name, description, create_date) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, item.getCreated());
            st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Changes the item in the List by the id.
     * @param id input id.
     * @param item input Item.
     */
    public void replace(String id, Item item) {
        try (final PreparedStatement st = this.connection.prepareStatement(
                "UPDATE tasks SET name = ?, description = ?, create_date = ? WHERE id = ?;"
        )) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, item.getCreated());
            st.setInt(4, Integer.valueOf(id));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search and delete item by id.
     * @param id input id of item.
     */
    public boolean delete(String id) {
        int deleted = 0;
        try (final PreparedStatement st = this.connection.prepareStatement(
                "DELETE FROM tasks WHERE id = ?"
        )) {
            st.setInt(1, Integer.valueOf(id));
            deleted = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted > 0;
    }

    /**
     * Method deletes all rows in tables.
     * Danger! Only for tests method.
     */
    public void deleteAll() {
        try (final PreparedStatement st = this.connection.prepareStatement(
                "TRUNCATE tasks CASCADE"
        )) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return all elements.
     * @return list with all elements.
     */
    public List<Item> findAll() {
        List<Item> listOfNames = new ArrayList<>();
        try (final PreparedStatement st = this.connection.prepareStatement("SELECT * FROM tasks");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                listOfNames.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getTimestamp("create_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listOfNames;
    }

    /**
     * Searches for items by name and copies them to a list of names.
     * @param name input name to search.
     * @return list of elements with searched name.
     */
    public List<Item> findByName(String name) {
        List<Item> listOfNames = new ArrayList<>();
        try (final PreparedStatement st = this.connection.prepareStatement(
                "SELECT * FROM tasks WHERE name = ?"
        )) {
            st.setString(1, name);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    listOfNames.add(new Item(rs.getString("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfNames;
    }


    /**
     * Searches for items by id.
     * @param id input id to searching.
     * @return found item.
     */
    public Item findById(String id) {
        Item result = null;
        try (final PreparedStatement st = this.connection.prepareStatement(
                "SELECT * FROM tasks WHERE id = ?"
        )) {
            st.setInt(1, Integer.valueOf(id));
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    result = new Item(rs.getString("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method load properties.
     * @param name of properties file.
     */
    private void getProperties(String name) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(name)) {
            this.properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method creates a connection using parameters from properties.
     */
    private void getConnection() {
        String serverUrl = String.valueOf(this.properties.getProperty("url"));
        String username = String.valueOf(this.properties.getProperty("username"));
        String password = String.valueOf(this.properties.getProperty("password"));
        try {
            connection = DriverManager.getConnection(serverUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method checks the structure of database.
     */
    private void structureCheck() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("trackerinit.sql")) {
            importSQL(connection, is);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method imports sql.
     * @param connection to db.
     * @param is input stream.
     * @throws SQLException if something goes wrong.
     */
    private void importSQL(Connection connection, InputStream is) throws SQLException {
        try (Scanner s = new Scanner(is);
             Statement st = connection.createStatement()) {
            s.useDelimiter(";");
            while (s.hasNext()) {
                String line = s.next();
                if (line.trim().length() > 0) {
                    st.execute(line);
                }
            }
        }
    }
}