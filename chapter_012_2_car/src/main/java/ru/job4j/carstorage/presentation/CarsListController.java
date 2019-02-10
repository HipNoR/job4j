package ru.job4j.carstorage.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carstorage.logic.ValidateService;
import ru.job4j.carstorage.models.Car;
import ru.job4j.carstorage.models.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * //TODO comment
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.01.2019
 */
public class CarsListController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CarsListController.class.getName());

    private final ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("dd-MM-yyyy HH:mm"));

    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        List<Car> cars = service.getAllCars();
//        List<Person> cars = service.getAllPersons();
        String jsonCars = mapper.writeValueAsString(cars);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonCars);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
