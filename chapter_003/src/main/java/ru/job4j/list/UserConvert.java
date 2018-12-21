package ru.job4j.list;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(User::getId, user -> user));
    }
}
