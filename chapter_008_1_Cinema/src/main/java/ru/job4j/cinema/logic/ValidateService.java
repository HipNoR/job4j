package ru.job4j.cinema.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cinema.persistence.DBHall;
import ru.job4j.cinema.persistence.Person;
import ru.job4j.cinema.persistence.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Logic layer class in cinema app.
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 10.12.2018
 */
public class ValidateService {
    private static final Logger LOG = LogManager.getLogger(ValidateService.class.getName());

    private final DBHall dbHall = DBHall.getInstance();

    private final static ValidateService INSTANCE = new ValidateService();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * The method returns map of all seats in cinema hall.
     * @return map. Key = row, value = List of seats.
     */
    public Map<Long, List<Seat>> getAll() {
        return dbHall.getAllSeats();
    }




    public int reserveSeats(Person person, String[] seats) {
        int result = 1;
        if (!dbHall.addPerson(person)) {
            result = -1;
        } else if (!dbHall.reserveSeats(person, parseSeats(seats))) {
                result = 0;
        }
        return result;
    }

    /**
     * Method parse seats array to List of Seats.
     * @param reserved seats to be reserved.
     * @return list of seats.
     */
    private List<Seat> parseSeats(String[] reserved) {
        List<Seat> result = new ArrayList<>();
        for (String seat : reserved
                ) {
            String[] parsedSeat = seat.split("\\.");
            result.add(new Seat(Integer.parseInt(parsedSeat[0]), Integer.parseInt(parsedSeat[1])));
        }
        return result;
    }
}
