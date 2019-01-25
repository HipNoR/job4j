package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BrandedModelTest {

    private void doTest(BiConsumer<Session, BrandedModel> command) {
        try (final SessionFactory factory = new Configuration().configure().buildSessionFactory();
             final Session session = factory.openSession()) {
            try {
                BrandedModel brandedModel = new BrandedModel();
                session.beginTransaction();
                command.accept(session, brandedModel);
            } finally {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenAddNewBrandedModelAndGetThenTrue() {
        doTest((session, brandedModel) -> {
            Brand testBrand = new Brand("test brand");
            session.save(testBrand);
            Model testModel = new Model("test model");
            session.save(testModel);
            brandedModel.setBrand(testBrand);
            brandedModel.setModel(testModel);
            int id = (int) session.save(brandedModel);
            BrandedModel expected = new BrandedModel(
                    id,
                    new Brand(testBrand.getId(), "test brand"),
                    new Model(testModel.getId(), "test model")
            );
            assertThat(session.get(BrandedModel.class, id), is(expected));
        });
    }

    @Test
    public void whenDeleteAndGetThenFalse() {
        doTest((session, brandedModel) -> {
            Brand testBrand = new Brand("test brand");
            session.save(testBrand);
            Model testModel = new Model("test model");
            session.save(testModel);
            brandedModel.setBrand(testBrand);
            brandedModel.setModel(testModel);
            int id = (int) session.save(brandedModel);
            session.delete(brandedModel);
            assertNull(session.get(BrandedModel.class, id));
        });
    }

    @Test
    public void whenAddAndUpdateThenTrue() {
        doTest((session, brandedModel) -> {
            Brand testBrand = new Brand("test brand");
            session.save(testBrand);
            Model testModel = new Model("test model");
            session.save(testModel);
            brandedModel.setBrand(testBrand);
            brandedModel.setModel(testModel);
            int id = (int) session.save(brandedModel);
            testBrand.setName("test brand2");
            testModel.setName("test model2");
            BrandedModel expected = new BrandedModel(
                    id,
                    new Brand(testBrand.getId(), "test brand2"),
                    new Model(testModel.getId(), "test model2")
            );
            assertThat(session.get(BrandedModel.class, id), is(expected));
        });
    }
}