package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BrandTest {

    private void doTest(BiConsumer<Session, Brand> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                Brand brand = new Brand("test brand");
                session.beginTransaction();
                command.accept(session, brand);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenAddNewBrandThenTrue() {
        doTest((session, brand) -> {
            int id = (int) session.save(brand);
            Brand expected = new Brand(id, "test brand");
            assertThat(session.get(Brand.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteBrandAndGetThenNull() {
        doTest((session, brand) -> {
            int id = (int) session.save(brand);
            session.delete(brand);
            assertNull(session.get(Brand.class, id));
        });
    }

    @Test
    public void whenUpdateBrandThenTrue() {
        doTest((session, brand) -> {
            int id = (int) session.save(brand);
            brand.setName("test brand2");
            Brand expected = new Brand(id, "test brand2");
            assertThat(session.get(Brand.class, id), is(expected));
        });
    }
}