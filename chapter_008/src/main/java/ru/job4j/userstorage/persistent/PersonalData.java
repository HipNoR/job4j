package ru.job4j.userstorage.persistent;


/**
 * The class stores user personal data.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 28.11.2018
 */
public class PersonalData {
    private String name;
    private String email;
    private String country;
    private String city;

    public PersonalData(String name, String email, String country, String city) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return String.format("Personal data {name: %s, email: %s, country: %s, city: %s}", name, email, country, city);
    }
}
