package ru.job4j.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.logic.ValidateService;
import ru.job4j.cinema.persistence.Seat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * The servlet controls controls the display of the cinema hall on the html page.
 *
 * On GET sends the customer a map of rows and places, as well as information on their reserve.
 *
 * On POST sends the customer a map of rows and places, as well as information on their reserve in JSON format.
 * Post method used by ajax in html page.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 06.12.2018
 */
public class HallController extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<Long, List<Seat>> seats = validateService.getAll();
        req.setAttribute("seats", seats);
        req.getRequestDispatcher("/WEB-INF/view/Hall.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, List<Seat>> seats = validateService.getAll();
        String jsonSeats = mapper.writeValueAsString(seats);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonSeats);
        writer.flush();
    }
}
