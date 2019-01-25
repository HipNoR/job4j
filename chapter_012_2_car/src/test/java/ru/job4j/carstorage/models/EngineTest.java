package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EngineTest {

    private void doTest(BiConsumer<Session, Engine> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Engine engine = new Engine("test engine");
                session.beginTransaction();
                command.accept(session, engine);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenCreateNewBodyThenTrue() {
        doTest((session, engine) -> {
            int id = (int) session.save(engine);
            Engine expected = new Engine(id, "test engine");
            assertThat(session.get(Engine.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBodyAndGetThenNull() {
        doTest((session, engine) -> {
            int id = (int) session.save(engine);
            session.delete(engine);
            assertNull(session.get(Engine.class, id));
        });
    }

    @Test
    public void whenUpdateBodyThenTrue() {
        doTest((session, engine) -> {
            int id = (int) session.save(engine);
            engine.setName("test engine2");
            Engine expected = new Engine(id, "test engine2");
            assertThat(session.get(Engine.class, id), is(expected));
        });
    }
}