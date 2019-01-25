package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarTest {

    private void doTest(BiConsumer<Session, Car> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                session.beginTransaction();
                Person person = new Person("test person", "password", "roman", "test@mail", 599599);
                session.save(person);
                Brand brandT = new Brand("test brand");
                session.save(brandT);
                Model modelT = new Model("test model");
                session.save(modelT);
                BrandedModel brandedModel = new BrandedModel(brandT, modelT);
                session.save(brandedModel);
                Engine engine = new Engine("test engine");
                session.save(engine);
                Gearbox gearbox = new Gearbox("test gearbox");
                session.save(gearbox);
                Body body = new Body("test body");
                session.save(body);
                Colour colour = new Colour("test colour");
                session.save(colour);
                Car car = new Car(person, "test car", brandedModel, engine, gearbox, body, colour);
                command.accept(session, car);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenAddNewCarAndGetThenTrue() {
        doTest((session, car) -> {
            int id = (int) session.save(car);
            Car result = session.get(Car.class, id);
            Car expected = new Car(car.getPerson(), "test car", car.getBrandedModel(),
                    car.getEngine(), car.getGearbox(), car.getBody(), car.getColour()
            );
            expected.setId(id);
            assertThat(result, is(expected));
        });
    }

    @Test
    public void whenDeleteCatAngGetThenNull() {
        doTest((session, car) -> {
            int id = (int) session.save(car);
            session.delete(car);
            assertNull(session.get(Car.class, id));
        });
    }

    @Test
    public void whenUpdateCarThenTrue() {
        doTest((session, car) -> {
            int id = (int) session.save(car);
            car.setDescription("updated description");
            car.getEngine().setName("updated engine");
            Car result = session.get(Car.class, id);
            Car expected = new Car(
                    car.getPerson(), "updated description", car.getBrandedModel(),
                    car.getEngine(), car.getGearbox(), car.getBody(), car.getColour()
            );
            expected.setId(id);
            assertThat(result, is(expected));
        });
    }
}