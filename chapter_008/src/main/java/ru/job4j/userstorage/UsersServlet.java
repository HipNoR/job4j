package ru.job4j.userstorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to display all users.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.3$
 * @since 0.1
 * 05.11.2018
 */
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<User> users = ValidateService.getInstance().findAll();
        String role = null;
        for (User user : users) {
            if (user.getId() == Long.parseLong(session.getAttribute("uid").toString())) {
                role = user.getRole();
                break;
            }
        }
        session.setAttribute("role", role);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/view/UsersView.jsp").forward(req, resp);
    }
}
