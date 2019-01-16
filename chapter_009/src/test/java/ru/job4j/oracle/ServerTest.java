package ru.job4j.oracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenSendExitThanGoodBye() throws IOException {
        testServer("exit", String.format("GoodBye, my friend, let the force be with you!%s", LN));
    }

    @Test
    public void whenSendHelloThanHello() throws IOException {
        testServer(
                Joiner.on(LN).join("hello", "exit"),
                Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "",
                        "GoodBye, my friend, let the force be with you!", "")
        );
    }

    @Test
    public void whenSendRandomThanRandomAnswer() throws IOException {
        testServer(
                Joiner.on(LN).join("unsupported", "exit"),
                Joiner.on(LN).join("Random phrase.", "",
                        "GoodBye, my friend, let the force be with you!", "")
        );
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }
}