package ru.job4j.userstorage.persistent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.job4j.userstorage.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory storage bean
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version $
 * @since 0.1
 * 17.02.2019
 */
@Component
public class MemoryStorageImpl<T extends BaseEntity> implements Storage<T> {
    private static final Logger LOG = LogManager.getLogger(MemoryStorageImpl.class.getName());

    private final List<T> storage = new ArrayList<>();

    @Override
    public void add(T bean) {
        this.storage.add(bean);
        LOG.info("{} added", bean);
    }

    @Override
    public List<T> getAll() {
        return this.storage;
    }
}
