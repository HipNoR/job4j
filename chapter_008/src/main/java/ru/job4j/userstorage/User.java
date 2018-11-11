package ru.job4j.userstorage;

import java.util.Date;

/**
 * Simple model of data.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.10.2018
 */
public class User {
    private long id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    public User(long id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        createDate = new Date();
    }

    public User(long id, String name, String login, String email, Date createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("User id: %s, Name: %s, Login: %s, Email: %s, Create date: %s \n",
                id, name, login, email, createDate);
    }
}
