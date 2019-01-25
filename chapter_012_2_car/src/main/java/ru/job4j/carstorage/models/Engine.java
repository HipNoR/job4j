package ru.job4j.carstorage.models;

import java.util.Objects;

/**
 * The class describes the car's engine.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 21.01.2019
 */
public class Engine {

    private int id;
    private String name;

    public Engine() {
    }

    public Engine(int id) {
        this.id = id;
    }

    public Engine(String name) {
        this.name = name;
    }

    public Engine(int id, String name) {
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
        return String.format("Engine{id=%s, name=%s}", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id && Objects.equals(name, engine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
