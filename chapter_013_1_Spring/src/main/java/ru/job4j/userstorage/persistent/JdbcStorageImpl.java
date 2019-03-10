package ru.job4j.userstorage.persistent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;
import ru.job4j.userstorage.entity.BaseEntity;

import java.util.List;
import java.util.function.Function;

/**
 * //TODO comment
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version $
 * @since 0.1
 * 02.03.2019
 */
@Component
public class JdbcStorageImpl<T extends BaseEntity> implements Storage<T> {
    private static final Logger LOG = LogManager.getLogger(JdbcStorageImpl.class.getName());

    private final SessionFactory factory;
    private final Class<T> typeParameterClass;


    public JdbcStorageImpl() {
        this.factory = new Configuration().configure().buildSessionFactory();
        this.typeParameterClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), JdbcStorageImpl.class);
    }


    private <E> E tx(final Function<Session, E> command) {
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


    @Override
    public void add(T bean) {
        this.tx(session -> session.save(bean));
        LOG.info("{} added", bean);
    }

    @Override
    public List<T> getAll() {
       return this.tx(session -> {
           String query = String.format("FROM %s", typeParameterClass.getCanonicalName());
           return (List<T>) session.createQuery(query).list();
       });
    }
}
