package ru.job4j.oracle;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private static final String LN = System.getProperty("line.separator");

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private  ByteArrayInputStream testIn;
    private  ByteArrayOutputStream testOut;

    @Before
    public void setOut() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void setInput(String consoleInput) {
        testIn = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystem() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }


    private void testClient(String console, String serverInput, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        setInput(console);
        ByteArrayInputStream in = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.start();
        assertThat(testOut.toString(), is(expected));
    }

    @Test
    public void whenSendExitThanMessageAndShutdown() throws IOException {
        testClient(
                String.format("exit%s", LN),
                Joiner.on(LN).join("server message", "", ""),
                Joiner.on(LN).join("server message", "Shutdown chat!", "")
        );
    }
}