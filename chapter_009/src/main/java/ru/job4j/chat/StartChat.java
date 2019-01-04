package ru.job4j.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The main class to run the chat program.
 * You write any phrase, in response you receive a random phrase from the file.
 * You can pause the answering robot to say "стоп".
 * To resume the work of the robot write "продолжить".
 * To end the program, type "завершить".
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 03.01.2019
 */
public class StartChat {
    private static final Logger LOG = LogManager.getLogger(StartChat.class.getName());

    public static void main(String[] args) {
        try {
            ChatLogic chat = new ChatLogic();
            chat.startChat("phrases.txt");
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

}
