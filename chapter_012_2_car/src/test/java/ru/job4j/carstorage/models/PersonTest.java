package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PersonTest {

    private void doTest(BiConsumer<Session, Person> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Person person = new Person("test person", "password", "roman", "test@mail", 599599);
                session.beginTransaction();
                command.accept(session, person);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewPersonAndGetItThenTrue() {
        doTest((session, person) -> {
            int id = (int) session.save(person);
            Person expected = new Person("test person", "password", "roman", "test@mail", 599599);
            expected.setId(id);
            assertThat(session.get(Person.class, id), is(expected));
        });
    }

    @Test
    public void whenDeletePersonAndGetThenNull() {
        doTest((session, person) -> {
            int id = (int) session.save(person);
            session.delete(person);
            assertNull(session.get(Person.class, id));
        });
    }

    @Test
    public void whenUpdateDetachedPersonThenTrue() {
        doTest((session, person) -> {
            int id = (int) session.save(person);
            person.setPassword("updated");
            Person expected = new Person("test person", "updated", "roman", "test@mail", 599599);
            expected.setId(id);
            assertThat(session.get(Person.class, id), is(expected));
        });
    }


    @Test
    public void whenGetCarsListFromUserThenTrue() {
        doTest((session, person) -> {
            session.save(person);
            Brand testBrand = new Brand("test brand");
            session.save(testBrand);
            Model testModel = new Model("test model");
            session.save(testModel);
            BrandedModel testBM = new BrandedModel(testBrand, testModel);
            session.save(testBM);
            Engine testEngine = new Engine("test engine");
            session.save(testEngine);
            Gearbox testGearbox = new Gearbox("test gearbox");
            session.save(testGearbox);
            Body testBody = new Body("test body");
            session.save(testBody);
            Colour testColour = new Colour("test colour");
            session.save(testColour);
            Car car = new Car(person, "test car", testBM, testEngine, testGearbox, testBody, testColour);
            session.save(car);
            List<Car> cars = new ArrayList<>();
            cars.add(car);
            person.setCars(cars);
            session.update(person);

            Person resultPerson = session.get(Person.class, person.getId());
            List<Car> resultPersonCars = resultPerson.getCars();

            Person expectedPerson = new Person("test person", "password", "roman", "test@mail", 599599);
            expectedPerson.setId(person.getId());
            Car expectedCar = new Car(
                    expectedPerson, "test car",
                    new BrandedModel(
                            testBM.getId(),
                            new Brand(testBrand.getId(), "test brand"),
                            new Model(testModel.getId(), "test model")
                    ),
                    new Engine(testEngine.getId(), "test engine"),
                    new Gearbox(testGearbox.getId(), "test gearbox"),
                    new Body(testBody.getId(), "test body"),
                    new Colour(testColour.getId(), "test colour")
            );
            expectedCar.setId(car.getId());
            List<Car> expectedPersonCars = new ArrayList<>();
            expectedPersonCars.add(expectedCar);
            expectedPerson.setCars(expectedPersonCars);

            assertThat(resultPerson, is(expectedPerson));
            assertThat(resultPersonCars, is(expectedPersonCars));
        });
    }
}