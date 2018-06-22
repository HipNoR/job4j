package ru.job4j.list;

/**
 * Class User.
 * Describes the user. Contains the name, city and identifier fields.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class User {
    private int id;
    private String name;
    private String city;

    User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
