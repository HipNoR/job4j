package ru.job4j.carstorage.models;


import javax.persistence.*;
import java.util.Objects;

/**
 * The class describes a specific car model.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 21.01.2019
 */

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "bm_id", nullable = false)
    private BrandedModel brandedModel;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "gearbox_id", nullable = false)
    private Gearbox gearbox;

    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private Body body;

    @ManyToOne
    @JoinColumn(name = "colour_id", nullable = false)
    private Colour colour;

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public Car(Person person, String description, BrandedModel brandedModel,
               Engine engine, Gearbox gearbox, Body body, Colour colour) {
        this.person = person;
        this.description = description;
        this.brandedModel = brandedModel;
        this.engine = engine;
        this.gearbox = gearbox;
        this.body = body;
        this.colour = colour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public BrandedModel getBrandedModel() {
        return brandedModel;
    }

    public void setBrandedModel(BrandedModel brandedModel) {
        this.brandedModel = brandedModel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return String.format(
                "Car{id=%s, person = %s, name=%s, brand=%s, model=%s, engine=%s, gearbox=%s, body=%s, colour=%s}",
                id, person.getName(), description, brandedModel.getBrand().getName(), brandedModel.getModel().getName(),
                engine.getName(), gearbox.getName(), body.getName(), colour.getName()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && person.getId() == car.person.getId()
                && Objects.equals(description, car.description)
                && Objects.equals(brandedModel, car.brandedModel)
                && Objects.equals(engine, car.engine)
                && Objects.equals(gearbox, car.gearbox)
                && Objects.equals(body, car.body)
                && Objects.equals(colour, car.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, description, brandedModel, engine, gearbox, body, colour);
    }
}
