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

    public void update(Base model) throws OptimisticException {
        int id = model.getId();
        Base target = cont.get(id);
        String name = model.getName();
        int ver = target.getVersion();
        cont.computeIfPresent(id, new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer integer, Base base) {
                if (integer != cont.get(id).getVersion()) {
                    throw new OptimisticException("wrong version");
                }
                target.setName(name);
                return target;
            }
        });
    }

    public void delete(Base model) {
        cont.remove(model.getId());
        System.out.println(String.format("Thread %s removed %s", Thread.currentThread().getId(), model));
    }
}
