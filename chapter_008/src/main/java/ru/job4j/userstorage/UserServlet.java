package ru.job4j.userstorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

/**
 * Servlet works with user's store.
 * Presentation layout.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.10.2018
 */
public class UserServlet extends HttpServlet {
    /**
     * Validate class instance.
     */
    private final ValidateService validate = ValidateService.getInstance();
    /**
     * To generate a random id.
     */
    private final Random rn = new Random();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> users = validate.findAll();
        if (users.size() == 0) {
            writer.append("Storage is empty.");
        } else {
            users.forEach(user -> writer.append(user.toString()));
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String sid = req.getParameter("id");
        Long id;
        if (sid == null || sid.equals("")) {
            id = System.currentTimeMillis() + rn.nextInt();
        } else {
            id = Long.parseLong(sid);
        }
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(id, name, login, email);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String result = validate.init().doAction(action, user);
        writer.append(result);
        writer.flush();
    }
}
