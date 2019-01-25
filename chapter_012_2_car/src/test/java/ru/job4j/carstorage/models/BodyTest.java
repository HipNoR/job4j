package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BodyTest {

    private void doTest(BiConsumer<Session, Body> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Body body = new Body("test body");
                session.beginTransaction();
                command.accept(session, body);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewBodyThenTrue() {
        doTest((session, body) -> {
            int id = (int) session.save(body);
            Body expected = new Body(id, "test body");
            assertThat(session.get(Body.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBodyAndGetThenNull() {
        doTest((session, body) -> {
            int id = (int) session.save(body);
            session.delete(body);
            assertNull(session.get(Body.class, id));
        });
    }

    @Test
    public void whenUpdateBodyThenTrue() {
        doTest((session, body) -> {
            int id = (int) session.save(body);
            body.setName("test body2");
            Body expected = new Body(id, "test body2");
            assertThat(session.get(Body.class, id), is(expected));
        });
    }
}