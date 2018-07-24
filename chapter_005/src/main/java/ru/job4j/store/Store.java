package ru.job4j.store;

import java.util.Iterator;
import java.util.List;

/**
 * The class looks for changes in the two lists.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 24.07.2018
 */
public class Store {

    /**
     * Looks for changes in the two lists.
     * @param previous version of list.
     * @param current current version of list.
     * @return string of changes.
     */
    public static String diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        if (!previous.containsAll(current)) {
            Iterator<User> itr = current.iterator();
            while (itr.hasNext()) {
                User temp = itr.next();
                if (!previous.contains(temp)) {
                    boolean change = false;
                    for (User user : previous) {
                        if (temp.equalsId(user)) {
                            changed++;
                            change = true;
                            break;
                        }
                    }
                    if (!change) {
                        added++;
                    }
                }
            }

        }
        if (!current.containsAll(previous)) {
            Iterator<User> itr = previous.iterator();
            while (itr.hasNext()) {
                User temp = itr.next();
                if (!current.contains(temp)) {
                    boolean delete = true;
                    for (User user : current) {
                        if (temp.equalsId(user)) {
                            delete = false;
                            break;
                        }
                    }
                    if (delete) {
                        deleted++;
                    }
                }
            }
        }
        return String.format("Added: %s. Changed: %s. Deleted: %s.", added, changed, deleted);
    }


    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            boolean eq = false;
            if (equalsId(o)) {
                User user = (User) o;
                eq = name.equals(user.name);
            }
            return eq;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + id;
            result = 31 * result + name.hashCode();
            return result;
        }

        public boolean equalsId(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return this.id == user.id;
        }

        @Override
        public String toString() {
            return String.format("User: id = %s, name = %s.", id, name);
        }
    }
}
