package ru.job4j.carstorage.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carstorage.models.Car;
import ru.job4j.carstorage.models.Person;
import ru.job4j.carstorage.persistent.DBStore;

import java.util.List;

/**
 * //TODO comment
 *
 * @author Roman Bednyashov (hipnorosva@gmail.com)
 * @version 0.1$
 * @since 0.1
 * 31.01.2019
 */
public class ValidateService {
    private static final Logger LOG = LogManager.getLogger(ValidateService.class.getName());

    private static final ValidateService INSTANCE = new ValidateService();

    private final DBStore store;

    private ValidateService() {
        this.store = DBStore.getInstance();
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public List<Car> getAllCars() {
        return this.store.getAllCars();
    }

    public List<Person> getAllPersons() {
        return this.store.getAllPersons();
    }
}
