package ru.job4j.todolist.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.logic.ValidateService;
import ru.job4j.todolist.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Servlet controller web application todolist.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 17.01.2019
 */
public class ToDoController extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm"));

    private final ValidateService validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> tasks = validate.getAll();
        String jsonSeats = mapper.writeValueAsString(tasks);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonSeats);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            String description = req.getParameter("description");
            Item item = new Item();
            item.setDescript(description);
            validate.addItem(item);
        } else if (action.equals("perform")) {
            String id = req.getParameter("id");
            validate.performTask(id);
        }
    }
}
