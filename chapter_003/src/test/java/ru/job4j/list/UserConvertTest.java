package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenConvertListOfUsersToMapThen() {
        UserConvert converter = new UserConvert();
        List<User> users = List.of(
                new User(1, "Roman", "Kaluga"),
                new User(2, "Ivan", "Moscow"),
                new User(3, "Jack", "London")
        );
        Map<Integer, User> result = converter.process(users);
        assertThat(result.get(2).getName(), is("Ivan"));
    }
}
