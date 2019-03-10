package ru.job4j.userstorage.persistent;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.userstorage.entity.User;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUsersToStorageThenNotNull() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-test-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        storage.add(User.builder().name("roman").build());
        List<User> result = storage.getUsers();
        assertTrue(result.size() > 0);
        List<User> expected = List.of(User.builder().name("roman").build());
        assertThat(result, is(expected));
    }

}