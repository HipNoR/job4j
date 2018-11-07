package ru.job4j.userstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class implements the user storage in RAM.
 * Class is based on the Singleton pattern.
 * Persistent layout.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 01.11.2018
 */
public class MemoryStore implements Store {
    /**
     * Single instance of the class with eager initialization.
     */
    private static MemoryStore instance;

    /**
     * Users storage.
     * The user id is used as a key for the map.
     */
    private final ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();


    private MemoryStore() {
    }

    /**
     * Only one instance of this class will be created.
     * @return instance of class.
     */
    public static MemoryStore getInstance() {
        if (instance == null) {
            synchronized (MemoryStore.class) {
                if (instance == null) {
                    instance = new MemoryStore();
                }
            }
        }
        return instance;
    }

    /**
     * Add new User to storage.
     * @param user to be added.
     * @return False if a user with this id exists else returns true.
     */
    @Override
    public boolean add(User user) {
        return store.put(user.getId(), user) != null;
    }

    /**
     * Changes user if it exists.
     * @param user to update.
     * @return True if a user with this id exists and was updated else returns false.
     */
    @Override
    public boolean update(User user) {
        return store.replace(user.getId(), user) != null;
    }

    /**
     * Removes user by id.
     * @param id of user to be deleted.
     * @return True if was removed else false.
     */
    @Override
    public boolean delete(long id) {
        return store.remove(id) != null;
    }

    /**
     * Find all users in storage.
     * @return list of all users.
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        result.addAll(store.values());
        return result;
    }

    /**
     * Searches user by id.
     * @param id to search.
     * @return founded user.
     */
    @Override
    public User findById(long id) {
        return store.get(id);
    }

    /**
     * only for test.
     */
    public void deleteAll() {
        store.clear();
    }
}
