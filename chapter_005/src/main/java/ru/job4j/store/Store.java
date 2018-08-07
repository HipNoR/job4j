package ru.job4j.store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The class looks for changes in the two lists.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
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
        Map<Integer, String> map = new HashMap<>();

        int added = 0;
        int changed = 0;

        Iterator<User> prevItr = previous.iterator();
        while (prevItr.hasNext()) {
            User temp = prevItr.next();
            map.put(temp.id, temp.name);
        }
        Iterator<User> curntItr = current.iterator();
        while (curntItr.hasNext()) {
            User temp = curntItr.next();
            int tempID = temp.id;
            if (map.containsKey(tempID)) {
                if (!map.get(tempID).equals(temp.name)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        int deleted = previous.size() + added - current.size();
        return String.format("Added: %s. Changed: %s. Deleted: %s.", added, changed, deleted);
    }


    static class User {
        private final int id;
        private String name;

        public User(final int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
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
