package ru.job4j.userstorage.presentation;

import ru.job4j.userstorage.logic.Action;
import ru.job4j.userstorage.logic.Validate;
import ru.job4j.userstorage.logic.ValidateService;
import ru.job4j.userstorage.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Servlet works with user's store.
 * Presentation layout.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.5$
 * @since 0.1
 * 31.10.2018
 */
public class UserController extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final Validate validate = ValidateService.getInstance();
    /**
     * To generate a random id.
     */
    private final Random rn = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        Action.Type act = Action.Type.valueOf(action.toUpperCase());
        String sid = req.getParameter("id");
        Long id;
        if (sid == null || sid.equals("")) {
            id = System.currentTimeMillis() + rn.nextInt();
        } else {
            id = Long.parseLong(sid);
        }
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        User user = new User(id, name, login, password, role, email);
        String result = validate.doAction(act, user);
        req.setAttribute("result", result);
        List<User> users = validate.findAll();
        req.setAttribute("size", users.size());
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/view/UsersView.jsp").forward(req, resp);
    }
}
