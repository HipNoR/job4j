package ru.job4j.sort;

/**
 * User class.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class User implements Comparable<User> {
    private String name;
    private Integer age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }

    @Override
    public String toString() {
        return "User { Age: " + age + "." + " Name: " + name + ".}";
    }
}
