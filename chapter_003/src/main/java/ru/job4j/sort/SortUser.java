package ru.job4j.sort;

        import java.util.List;
        import java.util.Set;
        import java.util.TreeSet;

/**
 * Class for sorting the list of users by age.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class SortUser {

    /**
     * Method sorts the list of users by age and returns a set of users.
     * @param list input list of users.
     * @return sorted set of users by age.
     */
    public Set<User> sort(List<User> list) {
        Set<User> sorted = new TreeSet<>();
        sorted.addAll(list);
        return sorted;
    }
}
