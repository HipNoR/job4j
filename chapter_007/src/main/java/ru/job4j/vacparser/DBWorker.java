package ru.job4j.vacparser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;



/**
 * Class for working with DB.
 * Accepts a list of vacancies and stores them in a database.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 18.10.2018
 */
public class DBWorker implements AutoCloseable {
    /**
     * Logger for info output.
     */
    private final Logger log = LogManager.getLogger(DBWorker.class);

    /**
     * Connection to the database.
     */
    private Connection connection;

    /**
     * Loaded properties with connection options.
     */
    private Properties properties;

    /**
     * HashSet to protection the database from duplicates.
     */
    private HashSet<String> twinsChecker;

    public DBWorker(Properties properties) {
        this.properties = properties;
        try {
            setConnection();
            createStructure();
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
        hashPrepare();
    }

    /**
     * Add vacancy to database.
     * Checks for duplicates in database using @twinsChecker field.
     * @param vacancy to be added.
     * @return true if added or false.
     */
    public boolean addVacancy(Vacancy vacancy) {
        Boolean valid = false;
        String url = vacancy.getUrl();
        if (!twinsChecker.contains(url)) {
            Date date = new Date(vacancy.getPosted().getTime());
            String title = vacancy.getTitle();
            String desc = vacancy.getDescription();
            try (PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO vacancy (date, title, url, descript) VALUES (?, ?, ?,?)"
            )) {
                st.setDate(1, date);
                st.setString(2, title);
                st.setString(3, url);
                st.setString(4, desc);
                st.executeUpdate();
                valid = true;
            } catch (SQLException e) {
                log.error("ERROR", e);
            }
        }
        return valid;
    }

    /**
     * Method adds multiple vacancies to the database.
     * @param vacancies list of vacancies.
     */
    public void addMultiVac(List<Vacancy> vacancies) {
        int added = 0;
        for (Vacancy vacancy : vacancies) {
            if (addVacancy(vacancy)) {
                added++;
            }
        }
        setCurDate();
        log.info(String.format("Added %s new vacancies.", added));
    }

    /**
     * Method sets the date of the last check in the database.
     */
    public void setCurDate() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("UPDATE last_start_date SET date = CURRENT_DATE WHERE id = 1");
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
    }

    /**
     * Method gets the date of the last check from the database.
     * @return date of last check.
     */
    public Date lastStartDate() {
        Date lastDate = null;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT date FROM last_start_date WHERE id = 1");
            while (rs.next()) {
                lastDate = rs.getDate("date");
            }
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
        return lastDate;
    }


    /**
     * Method retrieves all vacancies from the database.
     * @return list of all vacancies.
     */
    public List<Vacancy> getAll() {
        List<Vacancy> result = new ArrayList<>();
        String query = "SELECT * FROM vacancy;";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Vacancy(rs.getInt("id"), rs.getDate("date"),
                        rs.getString("title"), rs.getString("url"), rs.getString("descript")));
            }
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
    }

    /**
     * Creates HasSet of all url's of vacancies from database.
     */
    private void hashPrepare() {
        twinsChecker = new HashSet<>();
        String query = "SELECT * FROM vacancy;";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                twinsChecker.add(rs.getString("url"));
            }
        } catch (SQLException e) {
            log.error("ERROR", e);
        }
    }

    /**
     * Creates a connection to the database.
     * @throws SQLException if exception.
     */
    private void setConnection() throws SQLException {
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Creates a database structure if it does not exist.
     * @throws SQLException if exception.
     */
    private void createStructure() throws SQLException {
        String table = properties.getProperty("sql.table_init");
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(table);
        }
    }
}
