package ru.job4j.userstorage;

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
 * @version 0.2$
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
            PreparedStatement st = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?)")) {
            st.setLong(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setDate(5, new Date(user.getCreateDate().getTime()));
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
            PreparedStatement st = connection.prepareStatement("UPDATE users SET name=?, login=?, email=? WHERE id=?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getId());
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
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                Date date = rs.getDate("created");
                User user = new User(id, name, login, email, date);
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
                String email = rs.getString("email");
                Date date = rs.getDate("created");
                user = new User(id, name, login, email, date);
            }
        } catch (SQLException e) {
            logger.error("User find by id ERROR!", e);
        }
        return user;
    }


    /**
     * Prepares structure of the table at first start.
     */
    private void prepareStructure() {
        try (Connection connection = SOURCE.getConnection();
            PreparedStatement st = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users ("
                            + "id BIGINT UNIQUE,"
                            + "name VARCHAR(50),"
                            + "login VARCHAR(50),"
                            + "email VARCHAR(50),"
                            + "created DATE\n"
                            + ");")
        ) {

            st.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating structure", e);
        }
    }
}
