package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 *
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 14.08.2018
 */

public class BaseCash {
    private volatile ConcurrentHashMap<Integer, Base> cont = new ConcurrentHashMap<>();

    public Base get(int id) {
        Base result = cont.get(id);
        System.out.println(String.format("Thread %s gets %s", Thread.currentThread().getId(), result));
        return result;
    }

    public void add(Base model) {
        cont.put(model.getId(), model);
        System.out.println(String.format("Thread %s add %s", Thread.currentThread().getId(), model));
    }

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

    public void delete(Base model) {
        cont.remove(model.getId());
        System.out.println(String.format("Thread %s removed %s", Thread.currentThread().getId(), model));
    }
}
