package ru.job4j.cinema.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cinema.logic.ValidateService;
import ru.job4j.cinema.persistence.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet controls the reservation of seats in the cinema.
 *
 * On GET takes an array of seats for the reserve and sends it to jsp.
 *
 * On POST checks if there is such a visitor in the database. If the visitor is missing, then add it.
 * If the visitor's phone number does not match the name in the database, then throws a message about it.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 12.12.2018
 */
public class ReserveController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ReserveController.class.getName());
    private final ValidateService validateService = ValidateService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] reserved = req.getParameterValues("seat");
        HttpSession session = req.getSession();
        session.setAttribute("reserved", reserved);
        req.getRequestDispatcher("/WEB-INF/view/OrderPayment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        int phone = Integer.parseInt(req.getParameter("phone"));
        Person person = new Person(name, phone);
        HttpSession session = req.getSession();
        String[] reserved = (String[]) session.getAttribute("reserved");
        int setReserve = validateService.reserveSeats(person, reserved);
        LOG.info(setReserve);
        String message;
        if (setReserve == -1) {
            message = "Этот номер телефона принадлежит другому клиенту";
            LOG.info("Person not added");
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/view/OrderPayment.jsp").forward(req, resp);
        } else  {
            if (setReserve == 0) {
                message = "Не удалось зарезервировать места, попробуйте выбрать снова";
                LOG.info("Order not complete");
                req.setAttribute("message", message);
            }
            req.getRequestDispatcher("/WEB-INF/view/OrderInfo.jsp").forward(req, resp);
        }
    }
}
