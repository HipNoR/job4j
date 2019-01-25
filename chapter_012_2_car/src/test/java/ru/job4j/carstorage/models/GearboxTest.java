package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GearboxTest {

    private void doTest(BiConsumer<Session, Gearbox> comand) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Gearbox gearbox = new Gearbox("test gearbox");
                session.beginTransaction();
                comand.accept(session, gearbox);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewBodyThenTrue() {
        doTest((session, gearbox) -> {
            int id = (int) session.save(gearbox);
            Gearbox expected = new Gearbox(id, "test gearbox");
            assertThat(session.get(Gearbox.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBodyAndGetThenNull() {
        doTest((session, gearbox) -> {
            int id = (int) session.save(gearbox);
            session.delete(gearbox);
            assertNull(session.get(Gearbox.class, id));
        });
    }

    @Test
    public void whenUpdateBodyThenTrue() {
        doTest((session, gearbox) -> {
            int id = (int) session.save(gearbox);
            gearbox.setName("test gearbox2");
            Gearbox expected = new Gearbox(id, "test gearbox2");
            assertThat(session.get(Gearbox.class, id), is(expected));
        });
    }

}