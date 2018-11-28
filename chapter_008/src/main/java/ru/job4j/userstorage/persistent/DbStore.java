package ru.job4j.userstorage.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * The class implements the user storage in DateBase.
 * Class is based on the Singleton pattern.
 *
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 11.11.2018
 */
public class DbStore implements Store {
    private final Logger logger = LoggerFactory.getLogger(DbStore.class);

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/user_store");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setValidationQuery("SELECT 1");
        prepareStructure();
    }

    /**
     * Returns INSTANCE.
     * @return INSTANCE of the class.
     */
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Adds user to the database.
     * @param user to be added.
     * @return true, if added.
     */
    @Override
    public boolean add(User user) {
        Boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?,?)")) {
            st.setLong(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.setString(6, user.getEmail());
            int locId = getLocationId(user.getCountry(), user.getCity());
            st.setInt(7, locId);
            st.setDate(8, new Date(user.getCreateDate().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            result = false;
            logger.error("User adding ERROR!", e);
        }
        return result;
    }

    /**
     * Updates existed user.
     * @param user to be updated.
     * @return true if updated.
     */
    @Override
    public boolean update(User user) {
        Boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE users SET name=?, login=?, password=?, role=?, email=? , loc_id=? WHERE id=?"
             )) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole());
            st.setString(5, user.getEmail());
            int locId = getLocationId(user.getCountry(), user.getCity());
            st.setInt(6, locId);
            st.setLong(7, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            result = false;
            logger.error("User update ERROR!", e);
        }
        return result;
    }

    /**
     * Delete user from database by id.
     * @param id to be deleted.
     * @return true, if deleted.
     */
    @Override
    public boolean delete(long id) {
        Boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            result = false;
            logger.error("User delete ERROR!", e);
        }
        return result;
    }

    /**
     * Finds all users in the database.
     * @return list of all users in the database.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users ORDER BY id")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String email = rs.getString("email");
                int locId = rs.getInt("loc_id");
                String[] location = getLocationById(locId);
                Date date = rs.getDate("created");
                PersonalData data = new PersonalData(name, email, location[0], location[1]);
                User user = new User(id, login, password, role, data, date);
                result.add(user);
            }
        } catch (SQLException e) {
            logger.error("User find all ERROR!", e);
        }
        return result;
    }

    /**
     * Finds single user in the database.
     * @param id to search.
     * @return found user.
     */
    @Override
    public User findById(long id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id =?")) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String email = rs.getString("email");
                int locId = rs.getInt("loc_id");
                String[] location = getLocationById(locId);
                Date date = rs.getDate("created");
                PersonalData data = new PersonalData(name, email, location[0], location[1]);
                user = new User(id, login, password, role, data, date);
            }
        } catch (SQLException e) {
            logger.error("User find by id ERROR!", e);
        }
        return user;
    }

    @Override
    public List<String> getCountries() {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT name FROM countries ORDER BY name")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String country = rs.getString("name");
                result.add(country);
            }
        } catch (SQLException e) {
            logger.error("Cant get countries! ERROR!", e);
        }
        return result;
    }

    @Override
    public List<String> getCities(String country) {
        List<String> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT cities.name FROM cities INNER JOIN countries "
                             + "ON cities.c_id = countries.c_id WHERE countries.name = ? ORDER BY cities.name")) {
            st.setString(1, country);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String city = rs.getString("name");
                result.add(city);
            }
        } catch (SQLException e) {
            logger.error("Cant get Cities! ERROR!", e);
        }
        return result;
    }

    private int getLocationId(String country, String city) {
        int id = -1;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT cities.loc_id FROM cities INNER JOIN countries "
                             + "ON cities.c_id = countries.c_id WHERE countries.name = ? AND cities.name = ?")) {
            st.setString(1, country);
            st.setString(2, city);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("loc_id");
            }
        } catch (SQLException e) {
            logger.error("Cant get ID! ERROR!", e);
        }
        return id;
    }

    private String[] getLocationById(int id) {
        String[] result = new String[2];
        if (id != -1) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement("SELECT countries.name AS country, cities.name AS city "
                         + "FROM cities INNER JOIN countries "
                         + "ON cities.c_id = countries.c_id "
                         + "WHERE cities.loc_id = ?;")) {
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    result[0] = country;
                    result[1] = city;
                }
            } catch (SQLException e) {
                logger.error("Cant get location! ERROR!", e);
            }
        }
        return result;
    }

    /**
     * Prepares structure of the table at first start.
     */
    private void prepareStructure() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS countries ("
                             + "c_id serial,"
                             + "name VARCHAR(50) UNIQUE,"
                             + "PRIMARY KEY (c_id));"
                             + "CREATE TABLE IF NOT EXISTS cities ("
                             + "loc_id serial,"
                             + "c_id INTEGER REFERENCES countries(c_id),"
                             + "name VARCHAR(50) UNIQUE,"
                             + "PRIMARY KEY (loc_id));"
                             + "CREATE TABLE IF NOT EXISTS users ("
                             + "id BIGINT UNIQUE,"
                             + "name VARCHAR(50),"
                             + "login VARCHAR(50) UNIQUE NOT NULL,"
                             + "password VARCHAR(50) NOT NULL,"
                             + "role VARCHAR(10),"
                             + "email VARCHAR(50),"
                             + "loc_id INTEGER REFERENCES cities(loc_id),"
                             + "created DATE"
                             + ");"
                             + "INSERT INTO users(ID, name,  login, password, role, created)"
                             + " VALUES (0, 'admin', 'root', 'root', 'admin', current_date);"
                             + "INSERT INTO countries (name) VALUES"
                             + "('Russia'),"
                             + "('Japan'),"
                             + "('USA');"
                             + "INSERT INTO cities (c_id, name) VALUES"
                             + "(1, 'Moscow'),"
                             + "(1, 'Saint-Petersburg'),"
                             + "(1, 'Kaluga'),"
                             + "(2, 'Tokyo'),"
                             + "(2, 'Kyoto'),"
                             + "(3, 'New York'),"
                             + "(3, 'Washington');"
             )
        ) {
            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating structure", e);
        }
    }
}
