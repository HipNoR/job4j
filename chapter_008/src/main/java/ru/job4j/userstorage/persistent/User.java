package ru.job4j.userstorage.persistent;

import java.util.Date;

/**
 * Simple model of data.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 31.10.2018
 */
public class User {
    private long id;
    private String login;
    private String password;
    private String role;
    private Date createDate;
    private PersonalData data;

    public User(long id, String login, String password, String role, PersonalData data) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.data = data;
        createDate = new Date();
    }

    public User(long id,  String login,  String password, String role, PersonalData data, Date createDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.data = data;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return data.getName();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return data.getEmail();
    }

    public String getCountry() {
        return data.getCountry();
    }

    public String getCity() {
        return data.getCity();
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("User id: %s, Name: %s, Login: %s, Email: %s, Country: %s, City: %s, Create date: %s",
                id, data.getName(), login, data.getEmail(), data.getCountry(), data.getCity(), createDate);
    }
}
