package ru.job4j.userstorage.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.userstorage.logic.Validate;
import ru.job4j.userstorage.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for managing location data.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 26.11.2018
 */
public class LocationController extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> countries = validate.getCountries();
        String jsonCountries = mapper.writeValueAsString(countries);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonCountries);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        List<String> result = validate.getCities(country);
        String json = mapper.writeValueAsString(result);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }
}
