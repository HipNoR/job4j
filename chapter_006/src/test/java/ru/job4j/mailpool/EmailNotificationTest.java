package ru.job4j.mailpool;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailNotificationTest {

    @Test
    public void whenAddTen() {
        EmailNotification not = new EmailNotification();

        for (int index = 0; index < 10; index++) {
            not.emailTo(new User("User #" + index, "Email #" + index));
        }

        not.close();
    }
}