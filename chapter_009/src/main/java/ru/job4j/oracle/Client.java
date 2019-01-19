package ru.job4j.oracle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client for Oracle chat.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 15.01.2019
 */
public class Client {
    private static final Logger LOG = LogManager.getLogger(Client.class.getName());
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            boolean working = true;
            String ask;
            String answer;
            do {
                ask = console.nextLine();
                out.println(ask);
                answer = in.readLine();
                while (!answer.isEmpty()) {
                    System.out.println(answer);
                    answer = in.readLine();
                }
                if (ask.equals("exit")) {
                    working = false;
                }
            } while (working);
            System.out.println("Shutdown chat!");
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 1111)) {
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            LOG.error(e);
        }
    }
}
