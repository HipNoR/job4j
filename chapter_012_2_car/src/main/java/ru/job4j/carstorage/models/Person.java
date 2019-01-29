package ru.job4j.carstorage.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The class describes the owner of the car.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 24.01.2019
 */

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, length = 50)
    private String login;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 10)
    private int phone;

    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Car> cars;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String login, String password, String name, String email, int phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return String.format(
                "Person{id=%s, login=%s, password=%s, name=%s, email=%s, phone=%s} person cars{%s}",
                id, login, password, name, email, phone, cars
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
        Person person = (Person) o;
        return id == person.id
                && phone == person.phone
                && Objects.equals(login, person.login)
                && Objects.equals(password, person.password)
                && Objects.equals(name, person.name)
                && Objects.equals(email, person.email)
                && Objects.equals(cars, person.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, email, phone, cars);
    }
}

