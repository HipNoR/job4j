package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ModelTest {

    private void doTest(BiConsumer<Session, Model> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Model model = new Model("test model");
                session.beginTransaction();
                command.accept(session, model);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewBodyThenTrue() {
        doTest((session, model) -> {
            int id = (int) session.save(model);
            Model expected = new Model(id, "test model");
            assertThat(session.get(Model.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBodyAndGetThenNull() {
        doTest((session, model) -> {
            int id = (int) session.save(model);
            session.delete(model);
            assertNull(session.get(Model.class, id));
        });
    }

    @Test
    public void whenUpdateBodyThenTrue() {
        doTest((session, model) -> {
            int id = (int) session.save(model);
            model.setName("test model2");
            Model expected = new Model(id, "test model2");
            assertThat(session.get(Model.class, id), is(expected));
        });
    }
}