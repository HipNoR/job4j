package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ColourTest {

    private void doTest(BiConsumer<Session, Colour> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Colour colour = new Colour("test colour");
                session.beginTransaction();
                command.accept(session, colour);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewBodyThenTrue() {
        doTest((session, colour) -> {
            int id = (int) session.save(colour);
            Colour expected = new Colour(id, "test colour");
            assertThat(session.get(Colour.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBodyAndGetThenNull() {
        doTest((session, colour) -> {
            int id = (int) session.save(colour);
            session.delete(colour);
            assertNull(session.get(Colour.class, id));
        });
    }

    @Test
    public void whenUpdateBodyThenTrue() {
        doTest((session, colour) -> {
            int id = (int) session.save(colour);
            colour.setName("test colour2");
            Colour expected = new Colour(id, "test colour2");
            assertThat(session.get(Colour.class, id), is(expected));
        });
    }

}