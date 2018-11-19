package ru.job4j.userstorage;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserCreateServletTest {
    @Test
    public void whenCreateNewUserAndSendPostRequest() throws ServletException, IOException {
        UserCreateServlet servlet = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("ustore")).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
    }
}