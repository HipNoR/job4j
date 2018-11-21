package ru.job4j.userstorage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class SignInControllerTest {

    Validate validate;
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;

    @Before
    public void createMocks() {
        validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/view/LoginView.jsp")).thenReturn(dispatcher);
    }

    @Test
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        new SignInController().doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenDoPostWithWrongLoginAndPassThenRedirect() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn("not existed");
        when(request.getParameter("password")).thenReturn("not existed");
        new SignInController().doPost(request, response);
        verify(dispatcher).forward(request, response);
        assertNull(request.getSession(false));
    }

    @Test
    public void whenDoPostWithRightLoginAndPassThenRedirect() throws ServletException, IOException {
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root");
        when(request.getSession()).thenReturn(session);
        new SignInController().doPost(request, response);
        verify(response).sendRedirect(String.format("%s/", request.getContextPath()));
    }
}