package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * A class for storing instances of a class base.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 14.08.2018
 */
public class BaseCash {
    private volatile ConcurrentHashMap<Integer, Base> cont = new ConcurrentHashMap<>();

    /**
     * The method returns an element from the repository by id.
     * @param id to search.
     * @return element if exist or null.
     */
    public Base get(int id) {
        Base result = cont.get(id);
        System.out.println(String.format("Thread %s gets %s", Thread.currentThread().getId(), result));
        return result;
    }

    /**
     * Adds a new element to the repository.
     * @param model to add.
     */
    public void add(Base model) {
        cont.put(model.getId(), model);
        System.out.println(String.format("Thread %s add %s", Thread.currentThread().getId(), model));
    }

    /**
     * Updates instance of Base class in repository.
     * Increments version of model.
     * @param model model for update.
     * @throws OptimisticException when versions do not match.
     */
    public void update(final Base model) throws OptimisticException {
        cont.computeIfPresent(model.getId(), new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer id, Base base) {
                System.out.println(String.format("Model version: %s, Base version %s", model.getVersion(), base.getVersion()));
                if (base.getVersion() != model.getVersion()) {
                    throw new OptimisticException("wrong version");
                }
                System.out.println(String.format("Thread %s modify %s", Thread.currentThread().getId(), model));
                model.versionUpdate();
                return model;
            }
        });
    }

    /**
     * Removes the model from the repository.
     * @param model to delete.
     */
    public void delete(Base model) {
        cont.remove(model.getId());
        System.out.println(String.format("Thread %s removed %s", Thread.currentThread().getId(), model));
    }

    public int size() {
        return cont.size();
    }
}
