package ru.job4j.vacparser;

import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DBWorkerTest {

    @Test
    public void whenAddNewVacancyThanTrue() throws SQLException {
        try (DBWorker dbWorker = new DBWorker(ConnectionRollback.create(this.init()))) {
            assertTrue(dbWorker.addVacancy(new Vacancy(LocalDate.now(), "testvacancy", "url.com", "just for tests")));
        }
    }

    @Test
    public void whenAddTheSameVacancyThanFalse() throws SQLException {
        try (DBWorker dbWorker = new DBWorker(ConnectionRollback.create(this.init()))) {
            dbWorker.addVacancy(new Vacancy(LocalDate.now(), "testvacancy", "url.com", "just for tests"));
            assertFalse(dbWorker.addVacancy(new Vacancy(LocalDate.now(), "testvacancy", "url.com", "just for tests")));
        }
    }

    @Test
    public void whenAddListOfVacanciesThanAdded() throws SQLException {
        try (DBWorker dbWorker = new DBWorker(ConnectionRollback.create(this.init()))) {
            Vacancy vacancy = new Vacancy(LocalDate.now(), "testvacancy", "url.com", "just for tests");
            List<Vacancy> vacancies = List.of(
                    vacancy
            );
            dbWorker.addMultiVac(vacancies);
            assertTrue(dbWorker.getAll().contains(vacancy));
        }
    }

    @Test
    public void whenAddCurrentDateThanTrue() throws SQLException {
        try (DBWorker dbWorker = new DBWorker(ConnectionRollback.create(this.init()))) {
            dbWorker.setCurDate();
            LocalDate expected = LocalDate.now();
            assertThat(dbWorker.lastStartDate(), is(expected));
        }
    }

    public Connection init() {
        try (InputStream in = DBWorker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver"));
            return DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static class ConnectionRollback {
        /**
         * Create connection with autocommit=false mode and rollback call, when connection is closed.
         * @param connection connection.
         * @return Connection object.
         * @throws SQLException possible exception.
         */
        public static Connection create(Connection connection) throws SQLException {
            connection.setAutoCommit(false);
            return (Connection) Proxy.newProxyInstance(
                    ConnectionRollback.class.getClassLoader(),
                    new Class[] {Connection.class },
                    (proxy, method, args) -> {
                        Object rsl = null;
                        if ("close".equals(method.getName())) {
                            connection.rollback();
                            connection.close();
                        } else {
                            rsl = method.invoke(connection, args);
                        }
                        return rsl;
                    }
            );
        }
    }
}