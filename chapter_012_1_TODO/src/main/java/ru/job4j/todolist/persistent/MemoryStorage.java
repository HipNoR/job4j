package ru.job4j.todolist.persistent;

import ru.job4j.todolist.models.Item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Class storage elements in the computer's RAM.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 16.01.2019
 */
public class MemoryStorage implements Storage {

    private static final MemoryStorage INSTANCE = new MemoryStorage();

    private final ConcurrentHashMap<Long, Item> storage = new ConcurrentHashMap<>();

    private final Random random = new Random();

    private MemoryStorage() {
    }

    public static MemoryStorage getInstance() {
        return INSTANCE;
    }

    @Override
    public long add(Item item) {
        long id = System.currentTimeMillis() + random.nextInt();
        item.setId(id);
        storage.put(item.getId(), item);
        return id;
    }

    @Override
    public void update(Item item) {
        storage.replace(item.getId(), item);
    }

    @Override
    public void delete(Item item) {
        storage.remove(item.getId());
    }

    @Override
    public Item getItemById(long id) {
        return storage.get(id);
    }

    @Override
    public List<Item> getAll() {
        return storage.values().stream()
                .sorted((Comparator.comparing(Item::getCreated)))
                .collect(Collectors.toList());
    }

    @Override
    public void clearList() {
        this.storage.clear();
    }
}
