package ru.job4j.oracle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Server for Oracle chat.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.2$
 * @since 0.1
 * 15.01.2019
 */
public class Server {
    private static final Logger LOG = LogManager.getLogger(Server.class.getName());
    private final Socket socket;

    /**
     * Collection of predefined answers.
     */
    private final Map<String, String> digest = new HashMap<>();

    public Server(Socket socket) {
        this.socket = socket;
        init();
    }

    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            boolean working = true;
            String ask;
            String answer;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                answer = digest.get(ask);
                if (answer == null) {
                    answer = "Random phrase.";
                }
                if ("exit".equals(ask)) {
                    working = false;
                }
                out.println(answer);
                out.println();
            } while (working);
            System.out.println("Shutdown server");
        }
    }

    private void init() {
        this.digest.put("hello", "Hello, dear friend, I'm a oracle.");
        this.digest.put("exit", "GoodBye, my friend, let the force be with you!");
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(1111).accept()) {
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            LOG.error(e);
        }
    }
}