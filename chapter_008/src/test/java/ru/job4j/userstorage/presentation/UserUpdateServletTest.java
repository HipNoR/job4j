package ru.job4j.userstorage.presentation;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserUpdateServletTest {
    @Test
    public void whenUpdateUserAndSendToUserController() throws ServletException, IOException {
        UserUpdateServlet servlet = new UserUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("ustore")).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
    }
}