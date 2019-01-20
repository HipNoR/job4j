package ru.job4j.todolist.persistent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.models.Item;

import java.util.List;

/**
 * The class provides data storage in the database. The Hibernate framework is used.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
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
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                return (long) session.save(item);
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("Add exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    @Override
    public void update(Item item) {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(item);
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("Update exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    @Override
    public void delete(Item item) {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(item);
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("Delete exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    @Override
    public Item getItemById(long id) {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                return session.get(Item.class, id);
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("GetById exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    @Override
    public List<Item> getAll() {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                return session.createQuery("from Item order by created").list();
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("GetAll exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }

    @Override
    public void clearList() {
        try (final Session session = this.factory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.createQuery("delete Item ").executeUpdate();
            } catch (Exception ex) {
                if (tx != null) {
                    tx.rollback();
                }
                LOG.error("Delete exception", ex);
                throw ex;
            } finally {
                if (tx != null) {
                    tx.commit();
                }
            }
        }
    }
}
