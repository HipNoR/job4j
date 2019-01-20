package ru.job4j.todolist.persistent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.models.Item;

import java.util.List;
import java.util.function.Function;

/**
 * The class provides data storage in the database. The Hibernate framework is used.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 17.01.2019
 */
public class HDBStorage implements Storage {
    private static final Logger LOG = LogManager.getLogger(HDBStorage.class.getName());

    private final static HDBStorage INSTANCE = new HDBStorage();

    /**
     * The session factory is one for the entire application.
     */
    private final SessionFactory factory;

    private HDBStorage() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public static HDBStorage getInstance() {
        return INSTANCE;
    }

    @Override
    public long add(Item item) {
        return this.tx(
                session -> (long) session.save((item))
        );
    }

    @Override
    public void update(Item item) {
        this.tx(
                session -> {
                    session.update(item);
                    return null;
                }
        );
    }

    @Override
    public void delete(Item item) {
        this.tx(
                session -> {
                    session.delete(item);
                    return null;
                }
        );
    }

    @Override
    public Item getItemById(long id) {
        return this.tx(
                session -> session.get(Item.class, id)
        );
    }

    @Override
    public List<Item> getAll() {
        return this.tx(
                session -> (List<Item>) session.createQuery("from Item order by created").list()
        );
    }

    @Override
    public void clearList() {
        this.tx(
                session -> session.createQuery("delete Item").executeUpdate()
        );
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
}
