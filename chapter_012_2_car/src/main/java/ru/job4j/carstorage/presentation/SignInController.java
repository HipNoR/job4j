package ru.job4j.carstorage.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carstorage.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * //TODO comment
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.01.2019
 */
public class SignInController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(SignInController.class.getName());
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/SignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        long uid = validateService.isRegistered(login, password);
        if (uid != -1) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("uid", uid);
            resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
        } else {
            req.setAttribute("error", "Sign in invalid");
            doGet(req, resp);
        }
    }
}
