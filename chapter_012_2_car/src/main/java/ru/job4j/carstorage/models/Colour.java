package ru.job4j.carstorage.models;

import java.util.Objects;

/**
 * The class describes the color of the car.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 21.01.2019
 */
public class Colour {
   private int id;
   private String name;

    public Colour() {
    }

    public Colour(int id) {
        this.id = id;
    }

    public Colour(String name) {
        this.name = name;
    }

    public Colour(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Colour{id=%s, name=%s}", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Colour colour = (Colour) o;
        return id == colour.id && Objects.equals(name, colour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
