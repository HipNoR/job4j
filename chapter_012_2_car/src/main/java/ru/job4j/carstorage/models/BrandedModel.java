package ru.job4j.carstorage.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * The class describes the model of a specific car brand.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 24.01.2019
 */

@Entity
@Table(name = "branded_model")
public class BrandedModel {

    @Id
    @Column(name = "bm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    public BrandedModel() {
    }

    public BrandedModel(int id) {
        this.id = id;
    }

    public BrandedModel(Brand brand, Model model) {
        this.brand = brand;
        this.model = model;
    }

    public BrandedModel(int id, Brand brand, Model model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("BrandedModel{id=%s, brand=%s, model=%s}", id, brand.getName(), model.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandedModel that = (BrandedModel) o;
        return id == that.id
                && Objects.equals(brand, that.brand)
                && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model);
    }
}
