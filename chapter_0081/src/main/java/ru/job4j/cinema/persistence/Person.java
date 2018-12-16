package ru.job4j.cinema.persistence;

import java.io.Serializable;
import java.util.Objects;

/**
 * Person model class.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 06.12.2018
 */
public class Person implements Serializable {
    private String name;
    private int phone;

    public Person(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Person {name: %s, phone; %s}", name, phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return phone == person.phone && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
