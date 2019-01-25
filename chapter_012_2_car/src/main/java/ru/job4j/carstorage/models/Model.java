package ru.job4j.carstorage.models;


import java.util.Objects;

/**
 * The class describes the car model.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 24.01.2019
 */
public class Model {
    private int id;
    private String name;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public Model(String name) {
        this.name = name;
    }

    public Model(int id, String name) {
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
        return String.format("Model{id=%s, name=%s}", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id && Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
