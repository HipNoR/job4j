package ru.job4j.springxml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.model.User;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUsersToStorageThenNotNull() {
        ApplicationContext context = new ClassPathXmlApplicationContext("xml-spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        storage.add(User.builder().name("roman").build());
        List<User> result = storage.getUsers();
        List<User> expected = List.of(User.builder().name("roman").build());
        assertThat(result, is(expected));
    }
}