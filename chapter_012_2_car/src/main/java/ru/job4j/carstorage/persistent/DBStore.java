package ru.job4j.carstorage.persistent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.carstorage.models.Car;
import ru.job4j.carstorage.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * //TODO comment
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.01.2019
 */
public class DBStore {
    private static final Logger LOG = LogManager.getLogger(DBStore.class.getName());

    private static final DBStore INSTANCE = new DBStore();

    /**
     * The session factory is one for the entire application.
     */
    private final SessionFactory factory;

    private DBStore() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public static  DBStore getInstance() {
        return INSTANCE;
    }


    public List<Car> getAllCars() {
        return this.tx(session -> (List<Car>) session.createQuery("from Car").list());
    }

    public List<Person> getAllPersons() {
        return this.tx(session -> (List<Person>) session.createQuery("from Person ").list());

    }




    private <T> T tx(final Function<Session, T> command) {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                return command.apply(session);
            } catch (final Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error(ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    private void tx(final Consumer<Session> command) {
        this.tx(session -> {
            command.accept(session);
            return Optional.empty();
        });
    }
}
