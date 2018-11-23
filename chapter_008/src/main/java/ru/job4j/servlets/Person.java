package ru.job4j.servlets;

import java.io.Serializable;
import java.util.Objects;

/**
 * Person model.
 * JavaBeans.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 22.11.2018
 */
public class Person implements Serializable {

    private String firstname;
    private String secondname;
    private String sex;
    private String description;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Person: {First name = %s; Second name = %s; Sex = %s; Description = %s}",
                firstname, secondname, sex, description);
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
        return Objects.equals(firstname, person.firstname)
                && Objects.equals(secondname, person.secondname)
                && Objects.equals(sex, person.sex)
                && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, secondname, sex, description);
    }
}
