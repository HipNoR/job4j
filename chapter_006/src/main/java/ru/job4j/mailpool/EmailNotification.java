package ru.job4j.mailpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for email notifications by using ThreadPool.
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.09.2018
 */
public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Method create message for user.
     * Send this message via send() method.
     * @param user
     */
    public void emailTo(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String subject = String.format("Notification %s to email %s", username, email);
        String body = String.format("Add a new event to %s", username);

        pool.execute(() -> send(subject, body, email));
    }

    /**
     * Sends an email to the user.
     * @param subject of message.
     * @param body of message.
     * @param email to send.
     */
    public void send(String subject, String body, String email) {
            System.out.println(subject);
            System.out.println(body);
    }

    public void close() {
        pool.shutdown();
    }
}
