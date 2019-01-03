package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class for working with the database.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 06.12.2018
 */
public class DBHall {
    private static final Logger LOG = LogManager.getLogger(DBHall.class.getName());

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBHall INSTANCE = new DBHall();

    private final Properties properties = new Properties();

    private DBHall() {
        getProperties("app.properties");
        SOURCE.setDriverClassName(String.valueOf(this.properties.getProperty("db.driver")));
        SOURCE.setUrl(String.valueOf(this.properties.getProperty("db.url")));
        SOURCE.setUsername(String.valueOf(this.properties.getProperty("db.username")));
        SOURCE.setPassword(String.valueOf(this.properties.getProperty("db.password")));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setValidationQuery("SELECT 1");
    }

    /**
     * Returns INSTANCE.
     * @return INSTANCE of the class.
     */
    public static DBHall getInstance() {
        return INSTANCE;
    }

    /**
     * Reserves seats fr Person via transaction.
     * @param person the person on whom the order is made.
     * @param seats to be reserved.
     * @return true if transaction complete else false.
     */
    public boolean reserveSeats(Person person, List<Seat> seats) {
        boolean reserved = false;
        int order = seats.size();
        int count = 0;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement st = connection.prepareStatement("UPDATE hall SET reserved=? "
                    + "WHERE row=? AND seat=? AND reserved IS NULL;")
            ) {
                st.setInt(1, person.getPhone());
                for (Seat seat : seats
                        ) {
                    st.setInt(2, seat.getRow());
                    st.setInt(3, seat.getSeat());
                    count += st.executeUpdate();

                }
            } catch (SQLException e) {
                LOG.error("ERROR at reserveSeats() method at statement", e);
                connection.rollback();
                LOG.info("rollback in ex block");
                connection.setAutoCommit(true);
            }
            if (count == order) {
                connection.commit();
                reserved = true;
                LOG.info("committed");
            } else {
                connection.rollback();
                LOG.info("rollback in order block");
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error("ERROR at reserveSeats() method at connection", e);
        }
        return reserved;
    }

    /**
     * The method adds Person to database.
     * Checks if person already exists or create new.
     * @param person person to add or check.
     * @return true if pair (name : phone) exists or new was added, else false.
     */
    public boolean addPerson(Person person) {
        boolean result = false;
        if (accountCheck(person)) {
            result = true;
            LOG.info("{} exists", person);
        } else {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement("INSERT INTO accounts(phone, name) VALUES (?, ?);")
            ) {
                st.setInt(1, person.getPhone());
                st.setString(2, person.getName());
                st.executeUpdate();
                result = true;
            } catch (SQLException e) {
                LOG.error("ERROR at addPerson() method", e);
            }
        }
        return result;
    }

    /**
     * All seats map.
     * @return map of all seats.
     */
    public Map<Long, List<Seat>> getAllSeats() {
        Map<Long, List<Seat>> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM hall WHERE row=? ORDER BY seat;")
        ) {
            int rows = getRows();
            for (long index = 1; index <= rows; index++) {
                st.setLong(1, index);
                List<Seat> seats = new ArrayList<>();
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    int row = rs.getInt("row");
                    int seat = rs.getInt("seat");
                    int reserve = rs.getInt("reserved");
                    seats.add(new Seat(row, seat, reserve));
                }
                result.put(index, seats);
            }
        } catch (SQLException e) {
            LOG.error("ERROR at getAllSeats() method", e);
        }
        return result;
    }

    /**
     * The method clear all reserves.
     */
    public void releaseAll() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE hall SET reserved=NULL;")
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error("ERROR at releaseAll() method", e);
        }
    }

    /**
     * Gets number of rows in cinema.
     * @return rows.
     */
    private int getRows() {
        int result = 0;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT COUNT(DISTINCT row) FROM hall;")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            LOG.error("ERROR at getRow() method", e);
        }
        return result;
    }

    /**
     * Checks is there the person in the base.
     * @param person to check.
     * @return true or false.
     */
    private boolean accountCheck(Person person) {
        boolean result = false;

        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT EXISTS(SELECT phone FROM accounts WHERE phone=? AND name=?)")
        ) {
            st.setInt(1, person.getPhone());
            st.setString(2, person.getName());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = rs.getBoolean("exists");
            }
        } catch (SQLException e) {
            LOG.error("ERROR at accountCheck() method", e);
        }
        return result;
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

}