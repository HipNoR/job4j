package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Converts strings of names to a user class.
 * Based on lambda expressions.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 14.09.2018
 */
public class UserConverter {
    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("User(name = %s)", name);
        }
    }

    public List<User> converter(List<String> names, Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(
                n -> users.add(op.apply(n))
        );
        return users;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Petr", "Nick", "Roman");
        UserConverter users = new UserConverter();
        List<User> data = users.converter(names, User::new);
        data.forEach(System.out::println);
    }
}
