package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;

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
        int oldVersion = target.getVersion();
        String name = model.getName();
        if (cont.get(id).getVersion() != oldVersion) {
            throw new OptimisticException("Wrong version");
        } else {
            target.setName(name);
            System.out.println(String.format("Thread %s update %s", Thread.currentThread().getId(), target));
        }

    }

    public void delete(Base model) {
        cont.remove(model.getId());
        System.out.println(String.format("Thread %s removed %s", Thread.currentThread().getId(), model));
    }
}
