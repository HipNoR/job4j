package ru.job4j.userstorage.presentation;

import ru.job4j.userstorage.logic.Validate;
import ru.job4j.userstorage.logic.ValidateService;
import ru.job4j.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to update existed user.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.4$
 * @since 0.1
 * 07.11.2018
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User user = validate.findById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/view/UpdateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ustore").forward(req, resp);
    }
}
