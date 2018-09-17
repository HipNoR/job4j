package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConverterTest {
    @Test
    public void whenConvertThreeNamesToThreeUsers() {
            List<String> names = Arrays.asList("Petr", "Nick", "Roman");
            UserConverter users = new UserConverter();
            List<UserConverter.User> data = users.converter(names, UserConverter.User::new);
            List<UserConverter.User> expected = new ArrayList<>();
            expected.add(new UserConverter.User("Petr"));
            expected.add(new UserConverter.User("Nick"));
            expected.add(new UserConverter.User("Roman"));
            assertThat(data, is(expected));
    }

    @Test (expected = NullPointerException.class)
    public void whenExceptionThrows() throws Exception {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConverter.Wrapper<Exception> ex = null;
        names.forEach(
                n ->  {
                    try {
                        UserConverter.badMethod();
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        if (!ex.isEmpty()) {
            throw ex.get();
        }
    }
}