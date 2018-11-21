package ru.job4j.userstorage.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.userstorage.logic.Action;
import ru.job4j.userstorage.logic.Validate;
import ru.job4j.userstorage.logic.ValidateService;
import ru.job4j.userstorage.logic.ValidateStub;
import ru.job4j.userstorage.persistent.User;

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

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserControllerTest {

    Validate validate;
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;

    @Before
    public void prepareMock() {
        validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void whenAddUserThanTrue() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Roman");
        when(request.getParameter("login")).thenReturn("gus");
        when(request.getParameter("password")).thenReturn("1");
        when(request.getParameter("role")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("Roman@mail");
        when(request.getRequestDispatcher("/WEB-INF/view/UsersView.jsp")).thenReturn(dispatcher);
        new UserController().doPost(request, response);
        verify(dispatcher).forward(request, response);
        assertThat(validate.findById(1).getName(), is("Roman"));
    }

    @Test
    public void whenUpdateUserThanTrue() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("Updated Admin");
        when(request.getParameter("login")).thenReturn("root");
        when(request.getParameter("password")).thenReturn("root");
        when(request.getParameter("role")).thenReturn("admin");
        when(request.getParameter("email")).thenReturn("admin@mail");
        when(request.getRequestDispatcher("/WEB-INF/view/UsersView.jsp")).thenReturn(dispatcher);
        new UserController().doPost(request, response);
        verify(dispatcher).forward(request, response);
        assertThat(validate.findById(0).getName(), is("Updated Admin"));
    }

    @Test
    public void whenDeleteExistedUserThanTrue() throws ServletException, IOException {
        User first = new User(2, "second", "second", "2", "user", "second@mail");
        validate.doAction(Action.Type.ADD, first);
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("2");
        when(request.getRequestDispatcher("/WEB-INF/view/UsersView.jsp")).thenReturn(dispatcher);
        new UserController().doPost(request, response);
        verify(dispatcher).forward(request, response);
        assertNull(validate.findById(2));
    }
}