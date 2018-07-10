package ru.job4j.generic;

import java.util.Iterator;

/**
 * Store of Base objects.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store = new SimpleArray<>(10);

    @Override
    public void add(T model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean valid = false;
        for (int index = 0; index < 10; index++) {
            if (id.equals(store.get(index).getId())) {
                store.set(index, model);
                valid = true;
                break;
            }
        }
        return valid;
    }

    @Override
    public boolean delete(String id) {
        boolean valid = false;
        Iterator<T> iter = store.iterator();
        while (iter.hasNext()) {
            if (id.equals(iter.next().getId())) {
                iter.remove();
                valid = true;
                break;
            }
        }
        return valid;
    }

    @Override
    public T findById(String id) {
        for (int index = 0; index < 10; index++) {
            if (id.equals(store.get(index).getId())) {
                return store.get(index);
            }
        }
        return null;
    }

    /**
     * Method only for tests.
     * @param index index of item.
     * @return item.
     */
    protected T byIndex(int index) {
        return store.get(index);
    }
}
