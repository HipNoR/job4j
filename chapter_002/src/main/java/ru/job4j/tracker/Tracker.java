package ru.job4j.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Class Tracker repository of requests.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.2
 */
public class Tracker implements AutoCloseable {

    private Connection connection;


    public Tracker() {
        getConnection();
        structureCheck();
    }

    /**
     * Add item to List.
     * @param item input object of class item.
     */
    public Item add(Item item) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO tasks (name, description, create_date)"
                    + "VALUES ('" + item.getName() + "', '" + item.getDesc() + "', CURRENT_TIMESTAMP)");
            st.close();
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
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("UPDATE tasks SET name = '" + item.getName() + "', description = '" + item.getDesc()
                    + "', create_date = CURRENT_TIMESTAMP WHERE id = " + Integer.parseInt(id));
            st.close();
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
        try {
            Statement st = connection.createStatement();
            deleted = st.executeUpdate("DELETE FROM tasks WHERE id = " + Integer.parseInt(id));
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted > 0;
    }

    /**
     * Return all elements.
     * @return list with all elements.
     */
    public List<Item> findAll() {
        List<Item> listOfNames = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                listOfNames.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getTimestamp("create_date")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listOfNames;
    }

    /**
     * Searches for items by name and copies them to a list of names.
     * @param key input name to search.
     * @return list of elements with searched name.
     */
    public List<Item> findByName(String key) {
        List<Item> listOfNames = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE name = '" + key + "'");
            while (rs.next()) {
                listOfNames.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getTimestamp("create_date")));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listOfNames;
    }

    /**
     * Searches for items by id.
     * @param id input id to searching.
     * @return found item.
     */
    public Item findById(String id) {
        Item result = null;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tasks WHERE id = " + Integer.parseInt(id));
            while (rs.next()) {
                result = new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("description"), rs.getTimestamp("create_date"));
            }
            rs.close();
            st.close();
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

    public void getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("D:\\Programming\\Projects\\job4j\\chapter_002\\src\\main\\resources\\connect.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverUrl = String.valueOf(properties.getProperty("SERVER_URL"));
        String username = String.valueOf(properties.getProperty("USERNAME"));
        String password = String.valueOf(properties.getProperty("PASSWORD"));
        try {
            connection = DriverManager.getConnection(serverUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void structureCheck() {
        try {
            importSQL(connection, new FileInputStream(new File("D:\\Programming\\Projects\\job4j\\chapter_002\\src\\main\\resources\\trackerinit.sql")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void importSQL(Connection connection, FileInputStream is) throws SQLException {
        Scanner s = new Scanner(is);
        s.useDelimiter(";");
        Statement st = null;
        try {
            st = connection.createStatement();
            while (s.hasNext()) {
                String line = s.next();
                if (line.trim().length() > 0) {
                    st.execute(line);
                }
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}