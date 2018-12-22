package ru.job4j.comparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for sorting the list of users by age.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.2
 */
public class SortUser {

    /**
     * Method sorts the list of users by age and returns a set of users.
     * @param list input list of users.
     * @return sorted set of users by age.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Method sorts the list of users by length of the names.
     * @param list input unsorted list.
     * @return sorted list.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort((o1, o2) -> {
            Integer size1 = o1.getName().length();
            Integer size2 = o2.getName().length();
            return size1.compareTo(size2);
        });
        return list;
    }

    /**
     * Method sorts the list of users by the names and age.
     * @param list input unsorted list.
     * @return sorted list.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(Comparator.comparing(User::getName).thenComparingInt(User::getAge));
        return list;
    }

}
