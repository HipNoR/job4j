package ru.job4j.userstorage.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.userstorage.entity.User;
import ru.job4j.userstorage.persistent.UserStorage;

/**
 * Main class of spring user storage.
 * Use "add" args parameter for adding users in database.
 * For ex:   //java -jar ImportUser.jar add Roman
 *
 * Use "showAll: args parameter for showing all users in database.
 * For ex:   //java -jar ImportUser.jar showAll
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 10.03.2019
 */
@Component
public class ImportUser {
    private static final Logger LOG = LogManager.getLogger(ImportUser.class.getName());

    @Autowired
    private UserStorage storage;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        ImportUser importUser = context.getBean(ImportUser.class);
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("add")) {
                importUser.storage.add(User.builder().name(args[1]).build());
            } else if (args[0].equalsIgnoreCase("showAll")) {
                importUser.storage.getUsers().forEach(LOG::info);
            } else {
                LOG.info("Unsupported operation! Try \"add\" or \"showAll\"");
            }
        } else {
            LOG.info("Empty argument!");
        }
    }

}
