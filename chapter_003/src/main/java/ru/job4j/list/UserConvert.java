package ru.job4j.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Class converts the list of users to map of users, where the key is equals to the user id.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class UserConvert {

    /**
     * Converts the list of users to map of users, where the key is equals to the user id.
     * @param list input list of Users.
     * @return map of users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            map.put(user.getId(), user);
        }
        return map;
    }
}
